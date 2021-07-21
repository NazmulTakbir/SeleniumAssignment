import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;

public class Navigator {
    private WebDriver driver;
    private int time = 300;
    private boolean pause;
    private String webBrowser;
    private JavascriptExecutor javascriptExecutor;

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
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    private void quitBrowser() {
        try {
            if(pause) Thread.sleep(time*4);
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
                testResult = errorMessage.toLowerCase().contains(expectedText.toLowerCase());
            }
            quitBrowser();
        } catch(Exception e) {
            System.out.println(e);
        }
        return testResult;
    }

    public boolean addProduct(String p_name, String price, String quantity, String discount, String warranty, String additionalOptions, String location, String expectedText) {
        boolean testResult = false;
        try {
            startBrowser();
            driver.get("http://127.0.0.1:8000");
            driver.manage().window().maximize();
            driver.findElement(By.id("email")).sendKeys("nazmultakbir98@gmail.com");
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("password")).sendKeys("12345678");
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("id_submit")).click();
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("p_name")).sendKeys(p_name);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("price")).sendKeys(price);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("quantity")).sendKeys(quantity);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("discount")).sendKeys(discount);
            if(pause) Thread.sleep(time);
            if( warranty.equals("0") ) driver.findElement(By.id("warranty_0")).click();
            else if( warranty.equals("1") ) driver.findElement(By.id("warranty_1")).click();
            if(pause) Thread.sleep(time);
            if( additionalOptions.length() == 4 ) {
                if( additionalOptions.charAt(0) == '1' ) driver.findElement(By.id("additional_options_0")).click();
                if( additionalOptions.charAt(1) == '1' ) driver.findElement(By.id("additional_options_1")).click();
                if( additionalOptions.charAt(2) == '1' ) driver.findElement(By.id("additional_options_2")).click();
                if( additionalOptions.charAt(3) == '1' ) driver.findElement(By.id("additional_options_3")).click();
            }
            for(int i=0; i<100; i++ ) javascriptExecutor.executeScript("window.scrollBy(0,2)");
            if(pause) Thread.sleep(time);
            Select locationSelector = new Select(driver.findElement(By.id("location")));
            locationSelector.selectByVisibleText(location);
            if(pause) Thread.sleep(time);
            driver.findElement(By.id("id_submit_home")).click();
            testResult = driver.getPageSource().toLowerCase().contains(expectedText.toLowerCase());
            if( !testResult && driver.getPageSource().toLowerCase().contains("<h1>product form</h1>") ) {
                String errorMessage = driver.findElement(By.id("p_name")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("price")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("quantity")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("discount")).getAttribute("validationMessage");
                errorMessage += driver.findElement(By.id("warranty_0")).getAttribute("validationMessage");
                testResult = errorMessage.toLowerCase().contains(expectedText.toLowerCase());
                System.out.println(errorMessage);
                System.out.println(expectedText);
            }
            quitBrowser();
        } catch(Exception e) {
            System.out.println(e);
        }
        return testResult;
    }
}
