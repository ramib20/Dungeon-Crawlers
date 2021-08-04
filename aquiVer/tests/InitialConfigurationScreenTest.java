import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;
import static org.testfx.api.FxAssert.verifyThat;
import static org.junit.jupiter.api.Assertions.*;
import controller.StartGame;
import view.InitialConfigurationScreen;
import model.Player;

public class InitialConfigurationScreenTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception { //moves to InitialConfigurationScreen
        StartGame controller = new StartGame();
        controller.start(primaryStage);
    }


    @Test //Rami Bouhafs
    public void testNameInput() {
        clickOn("Play");
        verifyThat("Name:", NodeMatchers.isNotNull());
        TextField nameText = new TextField();
        String testName = nameText.getText();
        write("testName"); //types "testName" into the Name: text box
        press(KeyCode.ENTER); //clicks enter to confirm name
        assertNotNull(nameText.getText()); //verifies text box isn't empty
        assertNotNull(testName); //verifies the string name in text box isn't null
    }

    @Test //Rami Bouhafs
    public void testDifficultiesBar() {
        clickOn("Play");
        verifyThat("Difficulty:", NodeMatchers.isNotNull());
        press(KeyCode.TAB); //tabs down to difficulty drop-down bar
        press(KeyCode.DOWN); //goes down to Medium Difficulty
        assertSame(InitialConfigurationScreen.getChosenDifficulty(), "Medium");
    }

    @Test //Shail Patel
    public void testStartingItemBar() throws InterruptedException {
        clickOn("Play");
        verifyThat("Starting Item:", NodeMatchers.isNotNull());
        clickOn("Sword");
        Thread.sleep(500);
        press(KeyCode.DOWN); //goes down to Axe
        Thread.sleep(500);
        assertSame(InitialConfigurationScreen.getChosenStartingItem(), "Axe");
    }

    @Test //Shail Patel
    public void testBackButton() {
        clickOn("Play");
        clickOn("Back");
        new StartGameWelcomeScreenTest().testAllElementsOnScreen();
    }

    @Test//Shail Patel
    public void testContinueButton() {
        new InitialConfigurationScreenTest().testNameInput();
        Player player = new Player(320, 310, 70, 90, "testName", "Easy", "Sword");
        clickOn("Continue");
        verifyThat("Money: " + player.getMoney(), NodeMatchers.isNotNull());
    }
}
