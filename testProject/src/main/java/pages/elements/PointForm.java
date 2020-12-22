package pages.elements;

import algorithms.enums.FieldStatus;
import org.openqa.selenium.By;
import waitUtil.WaitUtil;

public class PointForm {
    private static final String LOCATOR = "//div[contains(@class,'battlefield__rival')]//td[contains(@class,'battlefield-cell__%s')]//div[@data-y='%s' and @data-x='%s']";
    private static final String EMPTY_POINT_LOCATOR = "//div[contains(@class,'battlefield__rival')]//div[contains(@class,'battlefield-table-placeholder')]//td[contains(@class ,'battlefield-cell__empty')]";

    public By getLocator(int y, int x, FieldStatus fieldStatus) {
        return By.xpath(String.format(LOCATOR, fieldStatus.getTitle(), y, x));
    }

    public By getEmptyPoint() {
        return By.xpath(EMPTY_POINT_LOCATOR);
    }

    public boolean isPointStatusChangedWithWait(FieldStatus fieldStatus, int y, int x) {
        return WaitUtil.isElementVisibleWithWait(getLocator(y, x, fieldStatus));
    }
}
