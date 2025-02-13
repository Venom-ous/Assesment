package gmailLoginPageClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
private WebDriver driver;
    
    private By usernameField = By.id("identifierId");
    private By nextButton = By.xpath("//span[text()='Next']");
    private By passwordField = By.name("password");
    private By loginErrorMessage = By.xpath("//div[contains(text(), 'Wrong password')] | //div[contains(text(), 'Couldn’t find your Google Account')]");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }
    
    public void clickNext() {
        driver.findElement(nextButton).click();
    }
    
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public boolean isLoginSuccessful() {
        try {
            WebElement errorElement = driver.findElement(loginErrorMessage);
            return !errorElement.isDisplayed();
        } catch (Exception e) {
            return true; // If no error message is found, assume login is successful
        }
    }
}
