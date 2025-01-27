package booking.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class Driver {

    private static WebDriver obj;

    private Driver(){
    }

    @BeforeMethod
    public static WebDriver getDriver(){

        String browserName = ConfigReader.read("browser");

        if(obj==null) {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    obj.get("https://www2.destinationgotland.se/en/accommodation");
                    obj.manage().window().maximize();
                    obj.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    obj.get("https://www2.destinationgotland.se/en/accommodation");
                    obj.manage().window().maximize();
                    obj.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
               default:
                   obj = null;

            }

            return obj;

        }else {

            return obj;
        }
        }


        }