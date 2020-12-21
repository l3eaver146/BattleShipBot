package steps;

import algorithms.enums.FieldStatuses;
import browserFactory.Browser;
import org.openqa.selenium.WebElement;
import pages.GameplayPage;

public class GamePlayPageSteps {
    private static final GameplayPage gameplayPage = new GameplayPage();

    public static boolean isWin() {
        return gameplayPage.isYouWinLabelVisible();
    }

    public static WebElement findElement(int y, int x, FieldStatuses statuses) {
        return Browser.getInstance().getDriver().findElement(gameplayPage.getPoint().getLocator(y, x, statuses));
    }

    public static boolean isPointStatusEquals(FieldStatuses fieldStatuses, int y, int x) {
        return Browser.getInstance().getDriver().findElements(gameplayPage.getPoint().getLocator(y, x, fieldStatuses)).size() > 0; //checking if the list is empty
    }

    public static boolean isGameFinished() {
        return gameplayPage.isYouWinLabelVisible() || gameplayPage.isYouLoseLabelVisible() || gameplayPage.isOpponentLeaveGameLabelVisible();
    }
}
