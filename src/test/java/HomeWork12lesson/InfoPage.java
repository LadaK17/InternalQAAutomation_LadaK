package HomeWork12lesson;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class InfoPage {
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postalCodeField = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");

    public void setFirstNameField (String firstname){
        firstNameField.setValue(firstname);
    }

    public void setLastNameField (String lastName){
        lastNameField.setValue(lastName);
    }
    public void setPostalCodeField (String postalCode){
       postalCodeField.setValue(postalCode);
    }

    public void clickContinueButton (){
        continueButton.click();
    }

}
