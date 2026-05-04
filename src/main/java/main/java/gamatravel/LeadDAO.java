package main.java.gamatravel;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class LeadDAO {
    // Pegando a URL da variável de ambiente
    private final String URI = System.getenv("MONGO_URL");

    public void salvar(String nome, String destino) {
        System.out.println("DEBUG: Tentando conectar ao banco...");

        if (URI == null || URI.isEmpty()) {
            System.err.println("❌ ERRO CRÍTICO: A variável de ambiente MONGO_URL está VAZIA ou nula!");
            return;
        }

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("GamaTravelDB");
            MongoCollection<Document> collection = database.getCollection("leads");

            Document doc = new Document("nome", nome)
                    .append("destino", destino)
                    .append("data_cadastro", new java.util.Date());

            // O comando abaixo é o que realmente envia o dado
            collection.insertOne(doc);

            System.out.println("✅ SUCESSO REAL: O MongoDB confirmou o recebimento de: " + nome);

        } catch (Exception e) {
            System.err.println("❌ FALHA NO MONGODB: " + e.getMessage());
            e.printStackTrace(); // Isso vai mostrar exatamente onde quebrou
        }
    }
}