package HomeWork13lesson;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static HomeWork13lesson.DriverManagerHelper.DriverManagerHelper.init;
import static com.codeborne.selenide.Selenide.open;

public class SauceTest {
    private final LoginPage loginPage = new LoginPage();

    private final ProductPage productPage = new ProductPage();

    private final CartPage cartPage = new CartPage();
    private final InfoPage infoPage = new InfoPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    private final SuccessPurchasePage successPurchasePage  = new SuccessPurchasePage();

//    @BeforeMethod
//    public void setUp() {
//        // Initialize the driver using DriverManagerHelper
//        init("SauceTest");
//    }
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
