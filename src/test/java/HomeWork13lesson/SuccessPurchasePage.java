package HomeWork13lesson;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SuccessPurchasePage {
    private final SelenideElement thankYouMessage = $(".complete-header");
    public boolean isThankYouMessageDisplayed() {
        String expectedText = "Thank you for your order!";

        // Зачекаємо, доки елемент буде видимим
        thankYouMessage.shouldBe(visible);

        // Отримаємо текст елемента
        String actualText = thankYouMessage.text();

        // Порівнюємо отриманий текст з очікуваним
        boolean isDisplayed = actualText.equals(expectedText);

        // Виводимо в консоль результат
        System.out.println("Is 'Thank you for your order!' message displayed? " + isDisplayed);

        return isDisplayed;
    }
}
