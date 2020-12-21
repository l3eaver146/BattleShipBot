package elements;

import browserFactory.Browser;
import logger.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waitUtil.WaitUtil;

import java.util.List;

public abstract class BaseElement {
    private final String name;
    private final By locator;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    private WebDriver getDriver() {
        return Browser.getInstance().getDriver();
    }

    public By getLocator() {
        return this.locator;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVisibleWithWait() {
        try {
            this.waitForDisplayed();
            this.waitForClickable();
            Logger.info("Element " + name + "is visible : " + true);
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            Logger.error(exception.getMessage());
            return false;
        }
    }

    public boolean isVisible(){
        try
        {
            return Browser.getInstance().getDriver().findElement(locator).isDisplayed()
                    &&
                    isElementExists();

        }
        catch(NoSuchElementException exception)
        {
            return false;
        }
    }

    private boolean isElementExists() {
        return getDriver().findElements(locator).size() > 0;
    }

    public void click() {
        this.waitForDisplayed();
        this.waitForClickable();
        Logger.info("Click " + name);
        try {
            getDriver().findElement(locator).click();
        } catch (NoSuchElementException exception) {
            Logger.error(String.format("Element %s wasn't found", name));
        }
    }

    public String getAttributeValue(String parentTag, String attributeName) {
        return getDriver().findElement(By.xpath(parentTag)).getAttribute(attributeName);
    }

    public String getText() {
        Logger.info("Get inner text of " + name);
        return getDriver().findElement(locator).getText().trim();
    }

    public void waitForClickable() {
        Logger.info("Waiting for clickable : " + this.getName());
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), WaitUtil.getWaitingTime());
        wait.until(ExpectedConditions.elementToBeClickable(this.getLocator()));
    }

    public void waitForDisplayed() {
        Logger.info("Waiting for displayed : " + this.getName());
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), WaitUtil.getWaitingTime());
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getLocator()));
    }

    public void waitForDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), WaitUtil.getWaitingTime());
            Logger.info("Waiting for disappear : " + this.getName());
            wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLocator()));
        } catch (NoSuchElementException ex) {
            Logger.error(ex.getMessage() + "Element is exists; Time is over");
        }
    }

    public List<WebElement> findElements(By locator) {
        try {
            return Browser.getInstance().getDriver().findElement(this.locator).findElements(locator);

        } catch (NoSuchElementException exception) {
            throw new RuntimeException("Element doesn't exists");
        }
    }
}
