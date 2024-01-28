package HomeWork12lesson;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

public class SauceTest {
    private final LoginPage loginPage = new LoginPage();

    private final ProductPage productPage = new ProductPage();

    private final CartPage cartPage = new CartPage();
    private final InfoPage infoPage = new InfoPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    private final SuccessPurchasePage successPurchasePage  = new SuccessPurchasePage();


    @Test
    public void SauceTestCase (){
        loginPage.openLoginPage("https://www.saucedemo.com/");
        loginPage.setLogin("standard_user");
        loginPage.setPasswordField("secret_sauce");
        loginPage.clickLoginButton();
        productPage.checkProductList();
        productPage.clickAddToCart();
        productPage.clickCartButton();
        cartPage.checkCartItems("$7.99", "$9.99");
        cartPage.openInfoPage();
        infoPage.setFirstNameField("Katty");
        infoPage.setLastNameField("Pierce");
        infoPage.setPostalCodeField("664467");
        infoPage.clickContinueButton();
        checkoutOverviewPage.printTaxPercentage();
        checkoutOverviewPage.clickFinishButton();
        successPurchasePage.isThankYouMessageDisplayed();
    }


}
