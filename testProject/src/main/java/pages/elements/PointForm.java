package pages.elements;

import algorithms.enums.FieldStatuses;
import browserFactory.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PointForm {
    private static final String LOCATOR = "//div[contains(@class,'battlefield__rival')]//td[contains(@class,'battlefield-cell__%s')]//div[@data-y='%s' and @data-x='%s']";
    private static final String EMPTY_POINT_LOCATOR = "//div[contains(@class,'battlefield__rival')]//div[contains(@class,'battlefield-table-placeholder')]//td[contains(@class ,'battlefield-cell__empty')]";

    public By getLocator(int y, int x, FieldStatuses fieldStatuses) {
        return By.xpath(String.format(LOCATOR, fieldStatuses.getTitle(), y, x));
    }

    public By getEmptyPointLocator() {
        return By.xpath(EMPTY_POINT_LOCATOR);
    }

    public boolean waitForPointBecome(FieldStatuses fieldStatus, int y, int x) {
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), 1);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(y, x, fieldStatus)));
            return true;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }
}
