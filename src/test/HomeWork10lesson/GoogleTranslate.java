package HomeWork10lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class GoogleTranslate {

    @Test
    public void testTranslationEN() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=en&op=translate", "I'll study TESTNG cool.");
    }

    @Test
    public void testTranslationFR() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=fr&op=translate", "Je vais étudier le test.");
    }

    @Test
    public void testTranslationDE() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=de&op=translate", "Ich werde testng cool studieren.");
    }

    @Test
    public void testTranslationBG() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=bg&op=translate", "Ще проуча Testng Cool.");
    }

    @Test
    public void testTranslationLV() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=lv&op=translate", "Es studēšu testng atdzist.");
    }

    @Test
    public void testTranslationCS() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=cs&op=translate", "Budu studovat testng cool.");
    }

    @Test
    public void testTranslationPL() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=pl&op=translate", "Badam testng fajnie.");
    }

    @Test
    public void testTranslationFI() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=fi&op=translate", "Opiskelen testiä siistiä.");
    }

    @Test
    public void testTranslationES() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=es&op=translate", "Estudiaré testng genial.");
    }

    @Test
    public void testTranslationSW() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=sw&op=translate", "Nitasoma testng baridi.");
    }

    @Test
    public void testTranslationSV() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=sv&op=translate", "Jag studerar testng cool.");
    }

    @Test
    public void testTranslationEL() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=el&op=translate", "Θα μελετήσω το testng cool.");
    }

    @Test
    public void testTranslationTR() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=tr&op=translate", "Testng serin çalışacağım.");
    }

    @Test
    public void testTranslationVI() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=vi&op=translate", "Tôi sẽ học Testng Cool.");
    }

    @Test
    public void testTranslationPT() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=pt&op=translate", "Vou estudar testng legal.");
    }

    @Test
    public void testTranslationAR() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=ar&op=translate", "سأدرس testng بارد.");
    }

    @Test
    public void testTranslationIT() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=it&op=translate", "Studierò Testng Cool.");
    }

    @Test
    public void testTranslationJA() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=ja&op=translate", "テストをクールに勉強します。");
    }

    @Test
    public void testTranslationKO() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=ko&op=translate", "Testng Cool을 공부할 것입니다.");
    }

    @Test
    public void testTranslationAF() {
        performTranslationTest("https://translate.google.com.ua/?hl=en&sl=auto&tl=af&op=translate", "Ek studeer testng cool.");
    }

    private void performTranslationTest(String url, String expectedTranslation) {
        openGoogleTranslate(url);
        enterTextAndTranslate("Я круто вивчу TestNG.");
        verifyTranslation(expectedTranslation);
    }

    private void openGoogleTranslate(String url) {
        Selenide.open(url);
    }

    private void enterTextAndTranslate(String text) {
        $(".er8xn").setValue(text).pressEnter();
        $(".ryNqvb").shouldBe(Condition.visible);
    }

    private void verifyTranslation(String expectedTranslation) {
        String actualTranslation = $(".ryNqvb").getText();
        System.out.println("Translation: " + actualTranslation);
        Assert.assertEquals(actualTranslation, expectedTranslation);
    }
}
