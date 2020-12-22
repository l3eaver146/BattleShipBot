package elements;

import browserFactory.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TabList extends BaseElement {
    public TabList(By elementLocator, String name) {
        super(elementLocator, name);
    }

    public List<WebElement> getComponents() {
        return Browser.getInstance().getDriver().findElements(this.getLocator());
    }

    public void clickSpecificElement(int position) {
        WebElement component = (WebElement) getComponents().toArray()[position];
        component.click();
    }

    public boolean isTabListOpened() {
        return Browser.getInstance().getDriver().findElements(this.getLocator()).size() > 0;
    }
}
