package HomeWork12lesson.DriverManagerHelper;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

@UtilityClass
public class DriverManagerHelper {

    private static final Logger LOG = Logger.getLogger(String.valueOf(DriverManagerHelper.class));

    public static void init(String testName) {
        initDriver(testName);
    }

    public static void initDriver(String testName) {
        LOG.info("### starting driver...");
        configureSelenide(testName);
        BrowserUpProxy proxy = configProxy();
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT);
        LOG.info("### driver is ready to work...");
    }

    public static BrowserUpProxy configProxy() {
        BrowserUpProxy proxy = Objects.requireNonNull(WebDriverRunner.getSelenideProxy()).getProxy();
        proxy.addRequestFilter((httpRequest, httpMessageContents, httpMessageInfo) -> {
            httpRequest.headers().remove("Accept-Language");
            httpRequest.headers().add("X-Forwarded-For", "179.60.183.228");
            httpRequest.headers().add("Accept-Language", "en");
            return null;
        });
        return proxy;
    }

    public static void configureSelenide(String testName) {
        Configuration.proxyEnabled = false;
        Configuration.proxyHost = NetworkUtils.getMachineIpAddress();
        Configuration.fileDownload = FileDownloadMode.PROXY;
        Configuration.pageLoadTimeout = 120_000;
        setConfiguration(testName);
//
//        open("https://www.saucedemo.com/");
        WebDriverRunner.getWebDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        WebDriverRunner.getWebDriver().manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    public static void setConfiguration(String testName) {
//        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.headless = false;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "normal";
        Configuration.pageLoadTimeout = 15000;
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.browserCapabilities = capabilities(testName);
        SelenideLogger.addListener("allureSelenideListener", new AllureSelenide().screenshots(true).savePageSource(true));
    }
    public class NetworkUtils {

        public static String getMachineIpAddress() {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace(); // Handle the exception based on your needs
                return null; // Return a default value or handle the absence of IP address
            }
        }
    }
    public static DesiredCapabilities capabilities(String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        capabilities.setCapability("goog:chromeOptions", options);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("videoName", testName + ".mp4");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("sessionTimeout", "2m");
        capabilities.setCapability("timeZone", "Europe/Kiev");

        return capabilities;
    }
}
