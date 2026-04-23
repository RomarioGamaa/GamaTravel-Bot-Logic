import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class LeadDAO {private MongoCollection<Document> collection;

    // Construtor: Ele faz a conexão quando você cria o objeto LeadDAO
    public LeadDAO() {
        String uri = System.getenv("MONGODB_URI");
        if (uri != null) {
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("GamaTravelDB");
            this.collection = database.getCollection("leads");
        }
    }

    // Método para SALVAR um lead
    public void salvar(String nome, String destino) {
        Document doc = new Document("nome", nome)
                .append("destino", destino)
                .append("data_cadastro", new java.util.Date());
        collection.insertOne(doc);
    }

    // Método para BUSCAR todos os leads
    public void listarTodos() {
        for (Document doc : collection.find()) {
            System.out.println("---------------------------");
            System.out.println("👤 Nome: " + doc.getString("nome"));
            System.out.println("📍 Destino: " + doc.getString("destino"));
            System.out.println("📅 Cadastrado em: " + doc.get("data_cadastro"));
        }
        System.out.println("---------------------------");
    }
}
