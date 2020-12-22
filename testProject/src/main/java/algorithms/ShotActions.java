package algorithms;

import algorithms.enums.FieldStatus;
import algorithms.enums.ShotVariant;
import algorithms.models.Point;
import logger.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import pages.GameplayPage;
import pages.elements.PointForm;
import steps.GamePlayPageSteps;
import waitUtil.WaitUtil;

import java.util.ArrayList;
import java.util.Random;

import static steps.GamePlayPageSteps.isGameFinished;

public class ShotActions {
    public ShotActions(){
        gameplayPage = new GameplayPage();
    }

    private final GameplayPage gameplayPage;

    public PointForm getPoint() {
        return gameplayPage.getPoint();
    }

    public boolean isHittedAfterShot(ShotVariant shotVariant, Point point) {
        int tempX = point.getX();
        int tempY = point.getY();
        switch (shotVariant) { //shot at position from the current point
            case UP -> tempY -= 1;
            case DOWN -> tempY += 1;
            case RIGHT -> tempX += 1;
            case LEFT -> tempX -= 1;
            case CURRENT -> {
            }
            default -> {
                return false;
            }
        }
        this.pointShot(tempY, tempX);
        point.setX(tempX);
        point.setY(tempY);
        return this.getPoint().isPointStatusChangedWithWait(FieldStatus.HIT, tempY, tempX);
    }

    public void pointShot(int y, int x) {
        if (isGameFinished()) {
            return;
        }
        this.gameplayPage.waitForYourTurnLabelAppearsIfGameContinue();
        if (GamePlayPageSteps.isPointStatusEquals(FieldStatus.EMPTY, y, x)) {
            GamePlayPageSteps.findElement(y, x, FieldStatus.EMPTY).click();
            this.getPoint().isPointStatusChangedWithWait(FieldStatus.HIT, y, x);
        }
    }

    public void shotUntilShipDie(int y, int x) {
        if (this.shelling(ShotVariant.UP, y, x)) {
            Logger.info("Miss up");
        } else {
            if (this.shelling(ShotVariant.DOWN, y, x)) {
                Logger.info("Miss down");
            } else {
                if (this.shelling(ShotVariant.LEFT, y, x)) {
                    Logger.info("Miss left");
                } else {
                    this.shelling(ShotVariant.RIGHT, y, x);
                }
            }
        }
    }

    public boolean shelling(ShotVariant shotVariant, int y, int x) {
        int count = 0;
        Point point = new Point(y, x);
        while (count < 4) { //until the maximum possible number of shots in one direction is reached
            if (this.isHittedAfterShot(shotVariant, point)) {
                if (GamePlayPageSteps.isPointStatusEquals(FieldStatus.DONE, point.getY(), point.getX())) {
                    return true;
                } else {
                    count++;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void startRandomShelling() {
        ArrayList<WebElement> emptyPoints = this.gameplayPage.getEmptyPoints();
        int shots = emptyPoints.size();
        while (shots > 0) { //the number decreases with each new shot
            if (isGameFinished()) {
                return;
            }
            shots = emptyPoints.size();
            int index = new Random().nextInt(shots + 1); //random number generation boundaries
            this.gameplayPage.waitForYourTurnLabelAppearsIfGameContinue();
            try {
                WaitUtil.waitForElementClickable(emptyPoints.get(index)).click();
                emptyPoints.remove(index);
            }catch (IndexOutOfBoundsException | ElementClickInterceptedException ignored){

            }
        }
    }
}

