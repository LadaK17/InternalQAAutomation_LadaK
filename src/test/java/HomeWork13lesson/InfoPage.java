package HomeWork13lesson;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class InfoPage {
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postalCodeField = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");

    @Step("Set {firstname}")
    public void setFirstNameField (String firstname){
        firstNameField.setValue(firstname);
    }

    @Step ("Set {lastName}")
    public void setLastNameField (String lastName){
        lastNameField.setValue(lastName);
    }
    @Step ("Set {postalCode}")
    public void setPostalCodeField (String postalCode){
       postalCodeField.setValue(postalCode);
    }

    @Step ("Click Continue Button")
    public void clickContinueButton (){
        continueButton.click();
    }

}
