package tests;

import algorithms.GameplayAlgorithm;
import browserFactory.Browser;
import browserFactory.config.ApplicationConfig;
import fileUtil.FileUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.GamePlayPageSteps;
import steps.MainPageSteps;

public class BattleShipTests {

    @BeforeClass
    public static void setUp() {
        Browser.getInstance().getDriver().manage().window().maximize();
        ApplicationConfig appConfig = FileUtil.getApplicationConfig();
        Browser.getInstance().openUrl(appConfig.getProtocol() + appConfig.getServer() + appConfig.getResource());
    }

    @AfterClass
    public static void tearDown() {
        Browser.getInstance().exitDriver();
    }

    @Test
    public void battleShipTests() {
        Assert.assertTrue("Main page wasn't opened", MainPageSteps.wasPageOpened());
        MainPageSteps.chooseRandomOpponent();
        MainPageSteps.clickRandomlyButtonSeveralTimes(1, 15);
        MainPageSteps.startGame();
        GameplayAlgorithm.playGame();
        Assert.assertTrue("Something went wrong, you loose", GamePlayPageSteps.isWin());
    }
}
