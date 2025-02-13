package Base;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Util.ExtentReporterNG;
import Util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	
    public static ExtentReports extent = ExtentReporterNG.getReportObject();
	//public static ExtentReports extent;
	public static ExtentTest extentTest;



	public static void initialization(String testName, String Description) throws IOException {
	    //extent = ExtentReporterNG.getReportObject();
		extentTest = extent.createTest(testName);
		extentTest.info("<b>TestCase Description</b> :-" + "<b>" + Description + "</b>");
		String browserName = "Chrome";
		if (browserName.equals("Chrome")) {
			driver = WebDriverManager.chromedriver().create();
			/*
			 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
			 */
			/*
			 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+
			 * "/src/main/java/com/omnidocs/qa/driver/chromedriver.exe"); ChromeOptions ops
			 * = new ChromeOptions(); ops.addArguments("remote-allow-origins=*");
			 * ops.addArguments("disable-infobars"); // disabling infobars
			 * ops.addArguments("disable-extensions"); // disabling extensions
			 * ops.addArguments("disable-gpu"); // applicable to windows os only
			 * ops.addArguments("disable-dev-shm-usage"); // overcome limited resource
			 * problems ops.addArguments("no-sandbox"); // Bypass OS security model driver =
			 * new ChromeDriver(ops);
			 */
		} else if (browserName.equals("FireFox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.no_proxies_on", "localhost");
			profile.setPreference("javascript.enabled", false);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("marionette", false);
			FirefoxOptions options = new FirefoxOptions();
			options.getProfile();
			options.merge(capabilities);
			options.addPreference("browser.link.open_newwindow", 3);
			options.addPreference("browser.link.open_newwindow.restriction", 0);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);
			// WebDriver driver = new FirefoxDriver(options);
		} else if (browserName.equals("Edge")) {
			EdgeOptions options = new EdgeOptions();
			
			options.addArguments("--disable-cache");
			options.addArguments("--disable-notifications");
			
			// options.addArguments("--download.default_directory=" + downloadDirectory);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.gmail.com");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
	}
}
