package teste;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TesteSelenium {
    public static void main(String[] args) {
        // 1. Configurações para o Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Abre a tela cheia

        // 2. Inicia o Navegador
        System.out.println("🤖 Iniciando o robô de teste...");
        WebDriver driver = new ChromeDriver(options);

        try {
            // 3. O "Pulo do Gato": Navegar até um site
            driver.get("https://www.google.com");

            // 4. Validar se chegamos no lugar certo
            String titulo = driver.getTitle();
            System.out.println("✅ Teste concluído! O título da página é: " + titulo);
            //Buscar no navegador:
            driver.get("https://www.bing.com");
            // Faz o robô encontrar a barra de busca, digitar e dar Enter
            driver.findElement(org.openqa.selenium.By.name("q")).sendKeys("Melhores destinos Brasil", org.openqa.selenium.Keys.ENTER);
            System.out.println("✅ O robô pesquisou sozinho!");
            // Pequena pausa de 3 segundos só para abrir o navegador
            Thread.sleep(3000);

        } catch (Exception e) {
            System.err.println("❌ Erro no teste: " + e.getMessage());
        } finally {
            // 5. Fecha tudo ( para não travar PC com mil processos)
            driver.quit();
            System.out.println("🔌 Navegador fechado.");

        }

    }
}