package main.java.gamatravel;

public class EbookProduct {
    private String titulo;
    private double preco;

    public EbookProduct(String titulo, double preco) {
        this.titulo = titulo;
        this.preco = preco;
    }

    public void exibirOferta() {
        System.out.println("\n--- 📚 OFERTA EXCLUSIVA ---");
        System.out.println("Produto: " + titulo);
        System.out.println("Valor: R$ " + preco);
        System.out.println("Benefícios: Roteiro completo, mapas offline e dicas de visto.");
        System.out.println("--------------------------");
    }
}
