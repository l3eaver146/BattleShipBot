package pages;

import elements.*;
import org.openqa.selenium.By;

public abstract class Page {
    protected Page(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    protected final By locator;
    protected final String name;

    public boolean isPageOpened() {
        Label lblPage = new Label(this.locator, this.name + " unique element");
        return lblPage.isVisibleWithWait();
    }
}
