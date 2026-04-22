import java.util.ArrayList;
import java.util.Scanner;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class GamaTravelBot {
    public static void main(String[] args) {
        // 1. O Java busca o link seguro que você acabou de salvar no IntelliJ
        String uri = System.getenv("MONGODB_URI");

        // 2. Conectar usando essa variável
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("GamaTravelDB");
            MongoCollection<Document> collection = database.getCollection("leads");

            System.out.println("✅ [SISTEMA] Conectado com segurança via Variável de Ambiente!");

            // --- INÍCIO DA LÓGICA DO BOT ---
            Scanner scanner = new Scanner(System.in);
            ArrayList<Lead> listaDeLeads = new ArrayList<>();
            EbookProduct ebookTibet = new EbookProduct("Roteiro Secreto: Tibet", 47.00);

            int opcao = 0;

            while (opcao != 4) {
                System.out.println("\n--- GAMA TRAVEL SISTEMA ---");
                System.out.println("1. Comprar E-book (Vendas)");
                System.out.println("2. Agendar Consultoria (Leads)");
                System.out.println("3. Relatório de Leads (Admin)");
                System.out.println("4. Sair");
                System.out.print("Escolha: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Erro: Digite apenas o número da opção (1 a 4).");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        ebookTibet.exibirOferta();
                        System.out.print("Deseja receber o link de pagamento? (sim/nao): ");
                        String resposta = scanner.nextLine();
                        if (resposta.equalsIgnoreCase("sim")) {
                            System.out.println(">> Enviando link para o WhatsApp...");
                        }
                        break;

                    case 2:
                        System.out.println("\n>> Iniciando agendamento da GamaTravel...");
                        String nome = "";

                        while (nome.trim().isEmpty()) {
                            System.out.print("Qual o seu nome completo? ");
                            nome = scanner.nextLine();
                            if (nome.trim().isEmpty()) {
                                System.out.println("⚠️ Erro: O nome não pode ficar em branco.");
                            }
                        }

                        System.out.print("Para qual destino você deseja orçar? ");
                        String destino = scanner.nextLine();
                        if (destino.trim().isEmpty()) { destino = "Destino não informado"; }

                        // Salvando Localmente
                        Lead novoLead = new Lead(nome, destino);
                        listaDeLeads.add(novoLead);

                        // ---ENVIANDO PARA O MONGODB ---
                        Document docLead = new Document("nome", nome)
                                .append("destino", destino)
                                .append("data_cadastro", new java.util.Date());

                        collection.insertOne(docLead);

                        System.out.println("✅ [NUVEM] Dados salvos com sucesso no MongoDB!");
                        break;

                    case 3:
                        System.out.println("\n=== RELATÓRIO DE LEADS PARA AGÊNCIA ===");
                        if (listaDeLeads.isEmpty()) {
                            System.out.println("Nenhum lead cadastrado nesta sessão.");
                        } else {
                            for (Lead l : listaDeLeads) {
                                l.exibirDetalhes();
                            }
                        }
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("\n❌ ERRO CRÍTICO: " + e.getMessage());
        }
    }
}