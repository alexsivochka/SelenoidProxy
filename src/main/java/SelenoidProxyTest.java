import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import com.browserup.harreader.model.HarEntry;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenoidProxyTest {

    @BeforeSuite(alwaysRun = true)
    public void SetUpBrowser() {
        Configuration.browser = RemoteDriverBrowser.class.getName();
        Configuration.timeout = 15000;
        Configuration.startMaximized = true;
//        Configuration.proxyEnabled = true;
//        Configuration.fileDownload = PROXY;
    }

    @Test
    public void testSelenoidProxy() throws FileNotFoundException {

//        BrowserUpProxy server = new BrowserUpProxyServer();
//        server.start();
//        open("https://www.google.com");
//        List<HarEntry> entries = server.getHar().getLog().getEntries();
//        for (HarEntry entry : entries) {
//
//            Date date = entry.getStartedDateTime();
//
//
//            System.out.println("startedtime:" + date.toString() + " request:" + entry.getRequest().getMethod() + ":"
//                    + entry.getRequest().getUrl());
//
//        }

        open("https://the-internet.herokuapp.com/download");
        File file = $(byText("some-file.txt")).download();
        Assert.assertEquals(file.getName(), "some-file.txt");
    }


}
