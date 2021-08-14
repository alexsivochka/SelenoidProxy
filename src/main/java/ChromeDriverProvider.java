import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;

public class ChromeDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://192.168.56.10:4444/wd/hub").toURL(),
                    getDesiredCapabilities());
            driver.setFileDetector(new LocalFileDetector());

            return driver;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось подключиться к url");
        }
    }

    public DesiredCapabilities getDesiredCapabilities() {
        Object optionsBrowser = getChromeOption();;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionsBrowser);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("88");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("env", new String[]{"LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"});

        return capabilities;
    }

    public ChromeOptions getChromeOption() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "/home/selenium/Downloads");
//        chromePrefs.put("download.prompt_for_download", false);
//        chromePrefs.put("download.directory_upgrade", true);
//        chromePrefs.put("safebrowsing.enabled", false);
//        chromePrefs.put("plugins.always_open_pdf_externally", true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-application-cache");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");

        //path/to/chrome --pack-extension=extensionProxy
        //http://chromedriver.chromium.org/extensions
//        options.addExtensions(Paths.get("extensionProxy.crx").toFile());
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.addArguments("--lang=ru-Ru");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return  options;
    }

}
