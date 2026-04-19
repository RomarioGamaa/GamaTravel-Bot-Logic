public class Lead {
    private String nome;
    private String destino;

    //Construtor: define o que o Lead precisa ter para ser criado
    public Lead(String nome, String destino) {
        this.nome = nome;
        this.destino = destino;

    }

    //Metodos para exibir os dados (ajudar a ver se funcionou)
    public void exibirDetalhes() {
        System.out.println("\n --- Novo Lead Registrado ---");
        System.out.println("Nome:" + this.nome);
        System.out.println("Interesse: " + this.destino);
        System.out.println("----------------------------");

    }
}
