package algorithms;

import algorithms.enums.FieldStatus;
import algorithms.enums.ShotVariant;
import algorithms.models.Point;

import static steps.GamePlayPageSteps.isGameFinished;
import static steps.GamePlayPageSteps.isPointStatusEquals;

public class GameplayAlgorithm {
    private final ShotActions shotActions;

    public GameplayAlgorithm() {
        shotActions = new ShotActions();
    }

    public void playGame() {
        this.fillDiagonal(3, 0);
        this.fillDiagonal(7, 0);
        this.fillDiagonal(9, 2);
        this.fillDiagonal(9, 6);
        this.fillDiagonal(1, 0);
        this.fillDiagonal(5, 0);
        this.fillDiagonal(9, 0);
        this.fillDiagonal(9, 4);
        this.fillDiagonal(9, 8);
        shotActions.startRandomShelling();
    }

    private void fillDiagonal(int y, int x) {
        Point point;
        int breakPoint = x;
        while (y >= breakPoint) {
            if (isGameFinished()) {
                break;
            }
            point = new Point(y--, x++);
            boolean shotRes = shotActions.isHittedAfterShot(ShotVariant.CURRENT, point);
            if (shotRes && isPointStatusEquals(FieldStatus.HIT, y + 1, x - 1)) { //checking the status of the previous point
                shotActions.shotUntilShipDie(y + 1, x - 1); //finishing off the boat at the previous point
            } else if (!isPointStatusEquals(FieldStatus.EMPTY, y, x)) {
                y--;
                x++;
            }
        }
    }
}
