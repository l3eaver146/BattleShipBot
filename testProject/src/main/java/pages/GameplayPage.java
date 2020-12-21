package pages;

import elements.Label;
import org.openqa.selenium.By;
import pages.elements.PointForm;

public class GameplayPage extends Page {
    public GameplayPage() {
        super(By.xpath("//div[contains(text(),'Игра началась')]"), "Gameplay page");
    }

    private final Label lblOpponentLeaveGame = new Label(By.xpath("//div[@class='notification notification__rival-leave']"), "Opponent leave game label");
    private final Label lblYouLose = new Label(By.xpath("//div[contains(@class,'notification__game-over-lose')]"), "You lose label");
    private final Label lblYouWin = new Label(By.xpath("//div[contains(@class,'notification__game-over-win')]"), "You win label");
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
        return lblYouLose.isVisible();
    }

    public boolean isYouWinLabelVisible() {
        return lblYouWin.isVisible();
    }

    public void waitForYourTurnLabelAppears() {
        if (lblYouWin.isVisible() || lblYouLose.isVisible() || lblOpponentLeaveGame.isVisible() || lblGameStartYourTurn.isVisible()) {
            return;
        }
        lblBattleField.waitForDisappear();
    }
}
