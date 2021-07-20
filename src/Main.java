import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    private static WebDriver driver;
    private static String chromeDriver = "E:\\Main\\Semesters\\3_1\\CSE 307 - Software Engineering\\Assignment\\SeleniumProject\\chromedriver.exe";
    private static String edgeDriver = "E:\\Main\\Semesters\\3_1\\CSE 307 - Software Engineering\\Assignment\\SeleniumProject\\msedgedriver.exe";
    private static String firefoxDriver = "E:\\Main\\Semesters\\3_1\\CSE 307 - Software Engineering\\Assignment\\SeleniumProject\\geckodriver.exe";

    public static void main(String[] args) {
        try {
            String webBrowser = "firefox";

            if( webBrowser.equalsIgnoreCase("edge") ) {
                System.setProperty("webdriver.edge.driver", edgeDriver);
                driver = new EdgeDriver();
            }
            else if( webBrowser.equalsIgnoreCase("chrome") ) {
                System.setProperty("webdriver.chrome.driver", chromeDriver);
                driver = new ChromeDriver();
            }
            else if( webBrowser.equalsIgnoreCase("firefox") ) {
                System.setProperty("webdriver.firefox.driver", firefoxDriver);
                driver = new FirefoxDriver();
            }

            register("1705103@ugrad.cse.buet.ac.bd", "Nazmul Takbir", "22", "8801727498589", "1234", "1234");
            login("1705103@ugrad.cse.buet.ac.bd", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void login(String email, String password) throws Exception {
        driver.get("http://127.0.0.1:8000/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.findElement(By.id("id_email")).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(By.id("id_password")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.id("id_submit")).click();
    }

    private static void register(String email, String name, String age, String phoneNUmber, String password, String password2) throws Exception {
        driver.get("http://127.0.0.1:8000/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.findElement(By.id("id_register")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("id_email")).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(By.id("id_name")).sendKeys(name);
        Thread.sleep(1000);
        driver.findElement(By.id("id_age")).sendKeys(age);
        Thread.sleep(1000);
        driver.findElement(By.id("id_phone")).sendKeys(phoneNUmber);
        Thread.sleep(1000);
        driver.findElement(By.id("id_password")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.id("id_confirm_password")).sendKeys(password2);
        Thread.sleep(1000);
        driver.findElement(By.id("id_register")).click();
    }
}
