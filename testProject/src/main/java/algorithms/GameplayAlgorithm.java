package algorithms;

import algorithms.enums.FieldStatuses;
import algorithms.enums.ShotVariants;
import algorithms.models.Point;
import pages.GameplayPage;
import pages.elements.PointForm;

import static algorithms.ShotAlgorithm.*;
import static steps.GamePlayPageSteps.isGameFinished;
import static steps.GamePlayPageSteps.isPointStatusEquals;

public class GameplayAlgorithm {
    private static final GameplayPage gameplayPage = new GameplayPage();

    public static GameplayPage getGameplayPage() {
        return gameplayPage;
    }

    public static PointForm getPoint() {
        return gameplayPage.getPoint();
    }

    public static void playGame() {
        fillDiagonal(3, 0);
        fillDiagonal(7, 0);
        fillDiagonal(9, 2);
        fillDiagonal(9, 6);
        fillDiagonal(1, 0);
        fillDiagonal(5, 0);
        fillDiagonal(9, 0);
        fillDiagonal(9, 4);
        fillDiagonal(9, 8);
        startRandomShelling();
    }

    private static void fillDiagonal(int y, int x) {
        Point point;
        int breakPoint = x;
        while (y >= breakPoint) {
            if (isGameFinished()) {
                break;
            }
            point = new Point(y--, x++);
            shot(ShotVariants.CURRENT, point);
            if (isPointStatusEquals(FieldStatuses.HIT, y + 1, x - 1)) { //checking the status of the previous point
                killShip(y + 1, x - 1); //finishing off the boat at the previous point
            } else if (!isPointStatusEquals(FieldStatuses.EMPTY, y, x)) {
                y--;
                x++;
            }
        }
    }

    private static void killShip(int y, int x) {
        int tempX = x;
        int tempY = y;
        shotUntilShipDie(tempY, tempX);
    }
}
