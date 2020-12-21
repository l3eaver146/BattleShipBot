package browserFactory;

import browserFactory.config.FrameworkConfig;
import fileUtil.FileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    public static WebDriver initBrowser() {
        Logger.info("Creating driver");
        Browsers browsers = Browsers.getByName(FileUtil.<FrameworkConfig>getFrameworkConfig().getBrowser());
        switch (browsers) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver( new OptionsFactory().<ChromeOptions>setOptions(browsers));
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(new OptionsFactory().<FirefoxOptions>setOptions(browsers));
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new OptionsFactory().<ChromeOptions>setOptions(Browsers.CHROME));
            }
        }
    }
}
