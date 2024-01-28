package HomeWork13lesson;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

public class CartPage {
    private final SelenideElement price = $(".inventory_item_price");

    private final SelenideElement checkout = $("#checkout");

    public void checkCartItems(String... expectedPrices) {
        // Найти все элементы с ценой продукта в корзине
        $$(".cart_item .inventory_item_price").forEach(itemPrice -> {
            // Перевірити, що ціна відповідає одній з очікуваних цін
            boolean priceMatches = false;
            for (String expectedPrice : expectedPrices) {
                if (itemPrice.text().equals(expectedPrice)) {
                    priceMatches = true;
                    break;
                }
            }
            assertTrue(priceMatches, "Ціна " + itemPrice.text() + " не відповідає очікуваним цінам");
        });
    }

    public void openInfoPage (){
        checkout.click();
    }
}
