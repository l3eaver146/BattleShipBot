package algorithms;

import algorithms.enums.FieldStatuses;
import algorithms.enums.ShotVariants;
import algorithms.models.Point;
import browserFactory.Browser;
import logger.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import waitUtil.WaitUtil;

import java.util.ArrayList;
import java.util.Random;

import static algorithms.GameplayAlgorithm.getGameplayPage;
import static algorithms.GameplayAlgorithm.getPoint;
import static steps.GamePlayPageSteps.*;

public class ShotAlgorithm {
    public static boolean shot(ShotVariants shotVariant, Point point) {
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
        pointShot(tempY, tempX);
        point.setX(tempX);
        point.setY(tempY);
        return getPoint().waitForPointBecome(FieldStatuses.HIT, tempY, tempX);
    }

    public static void pointShot(int y, int x) {
        if (isGameFinished()) {
            return;
        }
        getGameplayPage().waitForYourTurnLabelAppears();
        if (isPointStatusEquals(FieldStatuses.EMPTY, y, x)) {
            findElement(y, x, FieldStatuses.EMPTY).click();
            getPoint().waitForPointBecome(FieldStatuses.HIT, y, x);
        }
    }

    public static void shotUntilShipDie(int y, int x) {
        if (shelling(ShotVariants.UP, y, x)) {
            Logger.info("Miss up");
        } else {
            if (shelling(ShotVariants.DOWN, y, x)) {
                Logger.info("Miss down");
            } else {
                if (shelling(ShotVariants.LEFT, y, x)) {
                    Logger.info("Miss left");
                } else {
                    shelling(ShotVariants.RIGHT, y, x);
                }
            }
        }
    }

    public static boolean shelling(ShotVariants shotVariant, int y, int x) {
        int count = 0;
        Point point = new Point(y, x);
        while (count < 4) { //until the maximum possible number of shots in one direction is reached
            if (shot(shotVariant, point)) {
                if (isPointStatusEquals(FieldStatuses.DONE, point.getY(), point.getX())) {
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

    public static void startRandomShelling() {
        ArrayList<WebElement> emptyPoints = (ArrayList<WebElement>) Browser.getInstance().getDriver().findElements(getPoint().getEmptyPointLocator());
        int shots = emptyPoints.size();
        while (shots > 0) { //the number decreases with each new shot
            if (isGameFinished()) {
                return;
            }
            getGameplayPage().waitForYourTurnLabelAppears();
            shots = emptyPoints.size();
            int index = new Random().nextInt(shots + 1); //random number generation boundaries
            try {
                WaitUtil.waitForElementClickable(emptyPoints.get(index)).click();
                emptyPoints.remove(index);
            } catch (ElementClickInterceptedException | IndexOutOfBoundsException ignored) {
            }
        }
    }
}

