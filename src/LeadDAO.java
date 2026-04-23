import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LeadDAO {private MongoCollection<Document> collection;

    public void deletarPorNome(String nome) {
        // Cria um filtro para achar o documento com o nome exato
        Document filtro = new Document("nome", nome);

        // Deleta o primeiro que encontrar
        var resultado = collection.deleteOne(filtro);

        if (resultado.getDeletedCount() > 0) {
            System.out.println("🗑️ Lead '" + nome + "' removido com sucesso!");
        } else {
            System.out.println("⚠️ Nenhum lead encontrado com o nome: " + nome);
        }
    }
    public void exportarParaCSV() {
        String nomeArquivo = "leads_exportados.csv";

        try (FileWriter fw = new FileWriter(nomeArquivo);
             PrintWriter pw = new PrintWriter(fw)) {

            // Cabeçalho do Excel
            pw.println("Nome;Destino;Data");

            for (Document doc : collection.find()) {
                pw.println(doc.getString("nome") + ";" +
                        doc.getString("destino") + ";" +
                        doc.get("data_cadastro"));
            }

            System.out.println("📄 Arquivo '" + nomeArquivo + "' gerado com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao exportar: " + e.getMessage());
        }
    }

    // Construtor: Ele faz a conexão quando cria o objeto LeadDAO
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
