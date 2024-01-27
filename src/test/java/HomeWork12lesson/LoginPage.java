package HomeWork12lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement userNameField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $(".error-message-container");

    public void openLoginPage(String url) {
        Selenide.open(url);
    }

    public void setLogin (String username){
        userNameField.setValue(username);
    }

    public void setPasswordField (String password){
        passwordField.setValue(password);
    }

    public void clickLoginButton (){
        loginButton.click();
    }

    public void checkErrorMessage (){
        errorMessage.shouldBe(Condition.visible);
    }
}
