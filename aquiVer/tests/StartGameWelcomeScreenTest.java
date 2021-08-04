import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;
import controller.StartGame;

public class StartGameWelcomeScreenTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartGame controller = new StartGame();
        controller.start(primaryStage);
    }

    // Andrew Mo
    @Test
    public void testAllElementsOnScreen() {
        verifyThat("Dungeon Crawlers", NodeMatchers.isNotNull());
        verifyThat("Play", NodeMatchers.isNotNull());
        verifyThat("Exit", NodeMatchers.isNotNull());
    }
    // Andrew Mo
    @Test
    public void canTransitionToNextScreen() {
        clickOn("Play");
        verifyThat("Name:", NodeMatchers.isNotNull());
        verifyThat("Difficulty:", NodeMatchers.isNotNull());
        verifyThat("Starting Item:", NodeMatchers.isNotNull());
        verifyThat("Back", NodeMatchers.isNotNull());
        verifyThat("Continue", NodeMatchers.isNotNull());
    }
}