package HomeWork13lesson;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutOverviewPage {
    private final SelenideElement tax = $(".summary_tax_label");
    private final SelenideElement total = $(".summary_total_label");

    private final SelenideElement finishButton = $("#finish");

    public void printTaxPercentage() {
        try {
            double totalCost = Double.parseDouble(total.getText().replaceAll("[^0-9.]", ""));
            double taxAmount = Double.parseDouble(tax.getText().replace("Tax: $", "").replaceAll("[^0-9.]", ""));

            if (totalCost != 0) {
                double taxPercentage = (taxAmount / totalCost) * 100;
                System.out.println("Відсоток податку: " + taxPercentage);
            } else {
                System.out.println("Помилка: Загальна сума дорівнює нулю.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка при перетворенні тексту в число: " + e.getMessage());
        }
    }
    public void clickFinishButton (){
        finishButton.click();
    }
}
