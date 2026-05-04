
package main.java.gamatravel;

public class Lead {
    private String nome;
    private String destino;

    public Lead(String nome, String destino) {
        this.nome = nome;
        this.destino = destino;
    }

    public void exibirDetalhes() {
        System.out.println("\n --- Novo Lead Registrado ---");
        System.out.println("Nome: " + this.nome);
        System.out.println("Interesse: " + this.destino);
        System.out.println("----------------------------");
    }

    public static class LeadDAO {
        public void listarTodos() {
        }

        public void salvar(String nomeInput, String destinoInput) {
        }

        public void exportarParaCSV() {
        }

        public void deletarPorNome(String nomeParaDeletar) {
        }
    }
}