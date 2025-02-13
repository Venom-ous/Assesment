package gmailLoginLoginTestClasses;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Util.TestUtil;
import gmailLoginPageClasses.LoginPage;

public class loginPageTest extends BaseTest{
    private LoginPage loginPage;
    String TestDataSheetPath = System.getProperty("user.dir") + "/src/main/java/testData/GmailLoginTestData.xlsx";
	String sheetName1 = "GmailLogin";
	
	public loginPageTest() {
		super();
	}
	@BeforeMethod(groups = { "Group1" }, alwaysRun = true)
	public void setUp(ITestResult result) throws InterruptedException, IOException {
		 String TestName = result.getMethod().getMethodName();
		 String Description = result.getMethod().getDescription();
		initialization(TestName,Description);
		loginPage = new LoginPage(driver);
	}

    @Test(dataProvider = "loginData")
    public void testLogin(String UserName, String Password) throws InterruptedException {
        loginPage.enterUsername(UserName);
        Thread.sleep(1000);
        loginPage.clickNext();
        loginPage.enterPassword(Password);
        Thread.sleep(500);
        loginPage.clickNext();
        boolean actualResult = loginPage.isLoginSuccessful();
        boolean expectedResult = false;
        Assert.assertEquals(actualResult, expectedResult, "Login test failed");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws InvalidFormatException {
    	Object data[][] = TestUtil.getTestData(sheetName1, TestDataSheetPath);
        return data;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

