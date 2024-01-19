package HomeWork10lesson.Trello;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byClassName;

public class TrelloTestsChain {

    @Test
    public void loginTrello() {
        // Open Trello login page
        openLoginPage();

        // Enter email
        enterEmail("fiknunarku@gufum.com"); // Replace with your actual username

        // Enter password
        enterPassword("Qwertyu123!"); // Replace with your actual password

        // Perform login
        clickLoginButton();

        // Add any additional assertions or actions as needed for your test
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://trello.com/u/fiknunarku/boards"));

        checkMainPage();
    }

    private void openLoginPage() {
        Selenide.open("https://trello.com/en/login");
    }

    private void enterEmail(String text) {
        $(byClassName("form-field")).setValue(text).pressEnter();
    }

    private void enterPassword(String text) {
        $(byId("password")).shouldBe(Condition.appear).setValue(text).pressEnter(); // This line replaces waitUntil
    }

    private void clickLoginButton() {
        $(byId("login-submit")).click(); // Assuming this is a button responsible for login
    }

    private  void checkMainPage () {
        String currentUrl = WebDriverRunner.url();

        // Ваша логика проверки URL
        if (currentUrl.equals("https://trello.com/u/fiknunarku/boards")) {
            System.out.println("Поточний URL після логіну співпадає з очікуваним.");
        } else {
            System.out.println("Поточний URL після логіну не співпадає з очікуваним.");
            System.out.println("Поточний URL після логіну: " + currentUrl);
        }

    }

//    @Test
//    public void openDashboard() {
//        openDashboardPage();
//        // Add your test steps for the dashboard here
//    }
//
//    private void openDashboardPage() {
//        Selenide.open("https://trello.com/b/Xnj0k5in/my-trello-board");
//    }

}

