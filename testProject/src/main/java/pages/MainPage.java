package pages;

import elements.Button;
import org.openqa.selenium.By;

public class MainPage extends Page {
    public MainPage() {
        super(By.xpath("//div[contains(text(),'Разместите корабли.')]"), "Main page");
    }

    private final Button btnRandomly = new Button(By.xpath("//span[@class='placeships-variant-link']"), "Randomly button");
    private final Button btnStart = new Button(By.xpath("//div[@class='battlefield-start-button']"), "Start button");
    private final Button btnRandomOpponent = new Button(By.xpath("//a[@class='battlefield-start-choose_rival-variant-link']"),"Random opponent button");

    public void clickRandomlyButton() {
        btnRandomly.click();
    }

    public void clickStartButton() {
        btnStart.click();
    }

    public void clickRandOpponentButton() {
        btnRandomOpponent.click();
    }
}