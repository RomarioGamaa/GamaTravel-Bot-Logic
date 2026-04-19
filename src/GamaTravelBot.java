import java.util.ArrayList; // Importante para a lista
import java.util.Scanner;

public class GamaTravelBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Lista que guarda todos os leads que interagirem com o bot
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

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    ebookTibet.exibirOferta();
                    System.out.print("Deseja receber o link de pagamento? (sim/nao): ");
                    String resposta = scanner.nextLine();
                    if(resposta.equalsIgnoreCase("sim")) {
                        System.out.println(">> Enviando link para o WhatsApp...");
                    }
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Destino: ");
                    String destino = scanner.nextLine();

                    Lead novoLead = new Lead(nome, destino);
                    listaDeLeads.add(novoLead); // SALVANDO NA LISTA
                    System.out.println("✅ Agendamento pré-configurado!");
                    break;

                case 3:
                    System.out.println("\n=== RELATÓRIO DE LEADS PARA AGÊNCIA ===");
                    if(listaDeLeads.isEmpty()) {
                        System.out.println("Nenhum lead cadastrado ainda.");
                    } else {
                        for(Lead l : listaDeLeads) { // Loop que percorre a lista
                            l.exibirDetalhes();
                        }
                    }
                    break;
            }
        }
    }
}