package HomeWork10lesson;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SimpleTest {
    @Test
    @Description("Simple test")
    public void myFirstTest(){
        System.out.println("Hello!");
    }
}