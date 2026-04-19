import java.util.Scanner;

public class GamaTravelBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("--- Bem-vindo à GamaTravel! ---");

        while (opcao != 4) { // Loop do menu
            System.out.println("\nPrepare as malas! Como posso ajudar?");
            System.out.println("1. Explorar Destinos (E-book Tibet)");
            System.out.println("2. Agendar Consultoria");
            System.out.println("3. Suporte");
            System.out.println("4. Sair");

            System.out.print("\nEscolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println(">> Enviando link do E-book: Roteiro Secreto: Tibet!");
                        break;
                    // ... dentro do switch(opcao) ...

                    case 2:
                        System.out.println("\n>> Iniciando agendamento da GamaTravel...");

                        // Limpar o buffer do teclado (importante em Java após usar nextInt)
                        scanner.nextLine();

                        System.out.print("Qual o seu nome completo? ");
                        String nomeCliente = scanner.nextLine();

                        System.out.print("Para qual destino você deseja orçar? ");
                        String destinoCliente = scanner.nextLine();

                        // MÁGICA: Criando o objeto Lead com os dados reais!
                        Lead novoLead = new Lead(nomeCliente, destinoCliente);

                        System.out.println("\nPerfeito, " + nomeCliente + "! Seus dados foram pré-cadastrados.");
                        novoLead.exibirDetalhes();
                        break;
                    default:
                        System.out.println("Ops! Opção inválida. Escolha 1, 2 ou 3 (Opção A selecionada).");
                }
            } else {
                System.out.println("Por favor, digite apenas números.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
        scanner.close();
    }
}