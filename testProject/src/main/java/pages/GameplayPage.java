package pages;

import browserFactory.Browser;
import elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.elements.PointForm;

import java.util.ArrayList;

public class GameplayPage extends Page {
    public GameplayPage() {
        super(By.xpath("//input[contains(@class,'chat-teletype')]"), "Gameplay page");
    }

    private final Label lblOpponentLeaveGame = new Label(By.xpath("//div[@class='notification notification__rival-leave']"), "Opponent leave game label");
    private final Label lblLose = new Label(By.xpath("//div[contains(@class,'notification__game-over-lose')]"), "You lose label");
    private final Label lblWin = new Label(By.xpath("//div[contains(@class,'notification__game-over-win')]"), "You win label");
    private final Label lblGameStartYourTurn = new Label(By.xpath("//div[contains(text(),'ваш ход.')]"), "Your turn label");
    private final Label lblBattleField = new Label(By.xpath("//div[contains(@class,'battlefield__rival') and contains(@class, 'battlefield__wait')]"), "Battle field disabled");
    private final PointForm point = new PointForm();

    public PointForm getPoint() {
        return point;
    }

    public boolean isOpponentLeaveGameLabelVisible() {
        return lblOpponentLeaveGame.isVisible();
    }

    public boolean isYouLoseLabelVisible() {
        return lblLose.isVisible();
    }

    public boolean isYouWinLabelVisible() {
        return lblWin.isVisible();
    }

    public void waitForYourTurnLabelAppearsIfGameContinue() {
        if (!lblWin.isVisible() || !lblLose.isVisible() || !lblOpponentLeaveGame.isVisible() || !lblGameStartYourTurn.isVisible()) {
            lblBattleField.waitForDisappear();
        }
    }

    public ArrayList<WebElement> getEmptyPoints(){
        return (ArrayList<WebElement>) Browser.getInstance().getDriver().findElements(point.getEmptyPoint());
    }
}
