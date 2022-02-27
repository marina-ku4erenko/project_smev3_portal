package mari.ku.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import mari.ku.config.CredentialsConfig;
import mari.ku.helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        Configuration.remote = format("https://%s:%s@%s",
                credentials.login(),
                credentials.password(),
                System.getProperty("remoteUrl", "selenoid.autotests.cloud/wd/hub"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities = options;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "91");
        Configuration.browserSize = "1280x1024";

    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();

    }
}
