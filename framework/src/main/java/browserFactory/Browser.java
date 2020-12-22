package browserFactory;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class Browser {
    private Browser() {
        this.driver = BrowserFactory.initBrowser();
    }

    private WebDriver driver;
    private static Browser instance;
    private static final int LAST_WIDOW_HANDLE_NUMBER = -1;
    private static final int FIRST_WIDOW_HANDLE_NUMBER = 0;

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return instance.driver;
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void exitDriver() {
        driver.quit();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void switchToLastHandle() {
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window(windowHandles.toArray()[getWindowHandlesCount() - LAST_WIDOW_HANDLE_NUMBER].toString());
    }

    public void switchToDefaultContent() {
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window(windowHandles.toArray()[FIRST_WIDOW_HANDLE_NUMBER].toString());
    }

    public void closeTab() {
        driver.close();
    }

    public int getWindowHandlesCount() {
        return driver.getWindowHandles().size();
    }
}
