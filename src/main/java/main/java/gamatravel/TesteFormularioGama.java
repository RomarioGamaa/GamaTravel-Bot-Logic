package main.java.gamatravel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;

public class TesteFormularioGama {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            File file = new File("src/main/java/main/java/gamatravel/index.html");
            String caminhoArquivo = "file:///" + file.getAbsolutePath();
            driver.get(caminhoArquivo);

            // 1. Nossa "Lista de Passageiros" para o teste
            String[] nomes = {"João Silva", "Maria Santos", "Carlos Souza"};
            String[] destinos = {"Pipa", "Recife", "João Pessoa"};

            for (int i = 0; i < nomes.length; i++) {
                System.out.println("🤖 Testando cadastro " + (i + 1) + ": " + nomes[i]);

                // 2. Encontrar os elementos
                WebElement campoNome = driver.findElement(By.id("nome"));
                WebElement campoDestino = driver.findElement(By.id("destino"));
                WebElement botao = driver.findElement(By.id("btn-enviar"));

                // 3. Preencher os dados
                campoNome.sendKeys(nomes[i]);
                campoDestino.sendKeys(destinos[i]);

                Thread.sleep(1000); // Pausa para você ver o robô agindo

                botao.click();

                // 4. Validação rápida
                if (driver.findElement(By.id("mensagem")).isDisplayed()) {
                    System.out.println("✅ Sucesso para: " + nomes[i]);
                }

                Thread.sleep(1000);

                // 5. Limpa os campos para a próxima repetição
                campoNome.clear();
                campoDestino.clear();
            }

            System.out.println("\n🏆 Todos os testes de massa foram concluídos!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}