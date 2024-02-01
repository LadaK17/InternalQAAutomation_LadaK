package HomeWork13lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement userNameField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $(".error-message-container");

    @Step("Open Login Page {url}")
    public void openLoginPage(String url) {
        Selenide.open(url);
    }
    @Step("Set Login {username}")
    public void setLogin (String username){
        userNameField.setValue(username);
    }

    @Step("Set Password")
    public void setPasswordField (String password){
        passwordField.setValue(password);
    }
    @Step("Click Login Button")
    public void clickLoginButton (){
        loginButton.click();
    }

    public void checkErrorMessage (){
        errorMessage.shouldBe(Condition.visible);
    }
}
