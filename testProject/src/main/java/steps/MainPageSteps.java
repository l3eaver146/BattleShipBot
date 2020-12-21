package steps;

import logger.Logger;
import pages.MainPage;

import java.util.Random;
import java.util.stream.IntStream;

public class MainPageSteps {
    private static final MainPage mainPage = new MainPage();

    public static boolean wasPageOpened() {
        return mainPage.isPageOpened();
    }

    public static void clickRandomlyButtonSeveralTimes(int min, int max) {
        int timesNumber = new Random().nextInt(max - min + 1) + min;
        IntStream.range(1, timesNumber) //defining boundaries for generating a number. 1 - minimum number that can be generated
                .forEach(i -> mainPage.clickRandomlyButton());
        Logger.info("Positions changed");
    }

    public static void startGame() {
        mainPage.clickStartButton();
        Logger.info("Starting gaming");
    }

    public static void chooseRandomOpponent() {
        mainPage.clickRandOpponentButton();
        Logger.info("Random opponent fount");
    }
}
