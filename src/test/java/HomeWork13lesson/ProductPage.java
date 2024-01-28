package HomeWork13lesson;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.IntStream;

@Getter
@Accessors (fluent = true)
@NoArgsConstructor
public class ProductPage {
    private final ElementsCollection productList = $$x("//div[@class='inventory_container']/div/div");

    private final SelenideElement priceTag = $(".inventory_item_price");

    private final SelenideElement addToCart = $(".btn.btn_primary.btn_small.btn_inventory");

    private final SelenideElement cartButton = $(".shopping_cart_link");


    public void checkProductList(){
        productList.shouldHave(CollectionCondition.size(6));
    }

    public void clickAddToCart() {
        // Нажать "Add to Cart" для товаров с ценой $7.99
        clickForPrice("$7.99");

        // Нажать "Add to Cart" для товаров с ценой $9.99
        clickForPrice("$9.99");
    }

    private void clickForPrice(String price) {
        ElementsCollection items = $$("div.inventory_item");

        IntStream.range(0, items.size())
                .filter(i -> items.get(i).$(".inventory_item_price").text().equals(price))
                .findFirst()
                .ifPresent(i -> items.get(i).$(".btn_inventory").click());
    }

    public void clickCartButton (){
        cartButton.click();
    }


}
