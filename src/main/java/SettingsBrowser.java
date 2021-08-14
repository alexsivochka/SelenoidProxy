
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Paths;
import java.util.HashMap;

public class SettingsBrowser {

    public static ChromeOptions getChromeOption() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
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

        return options;
    }

    public static DesiredCapabilities getDesiredCapabilities() {
        Object optionsBrowser = SettingsBrowser.getChromeOption();
        ;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionsBrowser);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("88");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("env", new String[]{"LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"});

        return capabilities;
    }
}

