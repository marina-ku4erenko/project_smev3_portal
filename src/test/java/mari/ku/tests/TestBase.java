package mari.ku.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import mari.ku.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities = options;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = "1280x1024";
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        String remoteUrl = System.getProperty("remoteUrl");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        Configuration.remote = "https://" + user + ":" + password + "@" + remoteUrl;



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
