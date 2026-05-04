package main.java.gamatravel;

import java.util.ArrayList;
import java.util.Scanner;

public class GamaTravelBot {
    public static void main(String[] args) {
        // Colocamos o TRY logo no começo para proteger todo o programa
        try {
            // 1. Ferramentas
            Scanner scanner = new Scanner(System.in);
            Lead.LeadDAO dao = new Lead.LeadDAO(); // O DAO faz a conexão ao ser criado
            ArrayList<Lead> listaDeLeads = new ArrayList<>();

            // 2. Produtos
            EbookProduct ebookTibet = new EbookProduct("Roteiro Secreto: Tibet", 47.00);
            int opcao = 0;

            // Mudando o while para 6, já que o 6 agora será o SAIR
            while (opcao != 6) {
                System.out.println("\n--- GAMA TRAVEL SISTEMA ---");
                System.out.println("1. Comprar E-book (Vendas)");
                System.out.println("2. Agendar Consultoria (Leads)");
                System.out.println("3. Relatório de Leads (Nuvem)");
                System.out.println("4. Excluir um main.java.gamatravel.Lead");         // Nova opção!
                System.out.println("5. Exportar Leads (Excel)");   // Nova opção!
                System.out.println("6. Sair");
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
                        String nomeInput = "";

                        while (true) {
                            System.out.print("Qual o seu nome completo? ");
                            nomeInput = scanner.nextLine();

                            if (nomeInput.trim().isEmpty()) {
                                System.out.println("⚠️ Erro: O nome não pode ficar em branco.");
                            } else if (!isApenasLetras(nomeInput)) {
                                System.out.println("⚠️ Erro: Digite apenas letras e acentos. Números não são permitidos.");
                            } else {
                                break; // Se estiver tudo certo, sai do loop
                            }
                        }

                        System.out.print("Para qual destino você deseja orçar? ");
                        String destinoInput = scanner.nextLine();
                        if (destinoInput.trim().isEmpty()) {
                            destinoInput = "Destino não informado";
                        }

                        listaDeLeads.add(new Lead(nomeInput, destinoInput));
                        dao.salvar(nomeInput, destinoInput);

                        System.out.println("✅ main.java.gamatravel.Lead cadastrado e salvo no MongoDB!");
                        break;

                    case 3:
                        System.out.println("\n=== RELATÓRIO DE LEADS (DIRETO DA NUVEM) ===");
                        dao.listarTodos();
                        break;

                    case 4:
                        System.out.println("\n--- EXCLUSÃO DE LEAD ---");
                        System.out.print("Digite o nome EXATO do lead que deseja excluir: ");
                        String nomeParaDeletar = scanner.nextLine();

                        // Pergunta de confirmação
                        System.out.print("Tem certeza que deseja deletar " + nomeParaDeletar + "? (sim/nao): ");
                        if (scanner.nextLine().equalsIgnoreCase("sim")) {
                            dao.deletarPorNome(nomeParaDeletar);
                        } else {
                            System.out.println("Operação cancelada.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- EXPORTANDO DADOS ---");
                        dao.exportarParaCSV();
                        break;
                }
            }

            System.out.println("Encerrando o sistema... Até logo!");

        } catch (Exception e) {
            // Se o MongoDB falhar ou o IP mudar, o erro cai aqui
            System.err.println("\n❌ ERRO CRÍTICO NO SISTEMA: " + e.getMessage());
            e.printStackTrace(); // Ajuda a ver onde o erro aconteceu

        }
    }
    public static boolean isApenasLetras(String texto) {
        // Esse código (Regex) diz: Aceite de A-Z e caracteres acentuados
        return texto.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$");
    }
}