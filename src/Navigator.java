import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Paths;

public class Navigator {
    private WebDriver driver;
    private int time = 200;
    private boolean pause;
    private String webBrowser;

    public Navigator(String webBrowser, boolean pause) {
        this.webBrowser = webBrowser;
        this.pause = pause;

        String baseDirectory = Paths.get(System.getProperty("user.dir")).toString();
        if( webBrowser.equalsIgnoreCase("edge") ) {
            System.setProperty("webdriver.edge.driver", Paths.get(baseDirectory, "msedgedriver.exe").toString());
        }
        else if( webBrowser.equalsIgnoreCase("chrome") ) {
            System.setProperty("webdriver.chrome.driver", Paths.get(baseDirectory, "chromedriver.exe").toString());
        }
        else if( webBrowser.equalsIgnoreCase("firefox") ) {
            System.setProperty("webdriver.firefox.driver", Paths.get(baseDirectory, "geckodriver.exe").toString());
        }
    }

    private void startBrowser() {
        if( webBrowser.equalsIgnoreCase("edge") ) driver = new EdgeDriver();
        else if( webBrowser.equalsIgnoreCase("chrome") ) driver = new ChromeDriver();
        else if( webBrowser.equalsIgnoreCase("firefox") ) driver = new FirefoxDriver();
    }

    private void quitBrowser() {
        try {
            Thread.sleep(time*5);
            driver.quit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public boolean login(String email, String password, String expectedText)  {
        boolean testResult = false;
        try {
            startBrowser();
            driver.get("http://127.0.0.1:8000");
            driver.manage().window().maximize();
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("email")).sendKeys(email);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("password")).sendKeys(password);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("id_submit")).click();
            if(pause) Thread.sleep(time);
            testResult = driver.getPageSource().toLowerCase().contains(expectedText.toLowerCase());
            if( !testResult ) {
                String errorMessage = driver.findElement(By.id("email")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("password")).getAttribute("validationMessage");
                testResult = errorMessage.toLowerCase().contains(expectedText.toLowerCase());
            }
            quitBrowser();
        } catch (Exception e) {
            System.out.println(e);
        }
        return testResult;
    }

    public boolean register(String email, String name, String age, String phoneNUmber, String password, String confirmPassword, String expectedText) {
        boolean testResult = false;
        try {
            startBrowser();
            driver.get("http://127.0.0.1:8000/register/");
            driver.manage().window().maximize();
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("email")).sendKeys(email);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("name")).sendKeys(name);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("age")).sendKeys(age);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("phone")).sendKeys(phoneNUmber);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("password")).sendKeys(password);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("confirm_password")).sendKeys(confirmPassword);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("id_register")).click();
            if(pause) Thread.sleep(time);
            testResult = driver.getPageSource().toLowerCase().contains(expectedText.toLowerCase());
            if( !testResult ) {
                String errorMessage = driver.findElement(By.id("email")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("name")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("age")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("phone")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("password")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("confirm_password")).getAttribute("validationMessage");
                System.out.println(errorMessage);
                System.out.println(expectedText);
                testResult = errorMessage.toLowerCase().contains(expectedText.toLowerCase());
            }
            quitBrowser();
        } catch(Exception e) {
            System.out.println(e);
        }
        return testResult;
    }
}
