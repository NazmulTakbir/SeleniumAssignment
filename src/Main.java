import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        String chromeDriver = "E:\\Main\\Semesters\\3_1\\CSE 307 - Software Engineering\\Assignment\\SeleniumProject\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        WebDriver driver = new ChromeDriver();
        driver.get("https://moodle.cse.buet.ac.bd/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        driver.quit();
    }
}
