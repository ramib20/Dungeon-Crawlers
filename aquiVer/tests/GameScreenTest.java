import controller.StartGame;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Player;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.scene.shape.Rectangle;
import view.InitialConfigurationScreen;
import javafx.scene.control.Label;
import org.testfx.service.query.EmptyNodeQueryException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.testfx.api.FxAssert.verifyThat;


public class GameScreenTest extends ApplicationTest {

    private Rectangle player;
    private Rectangle monster;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartGame controller = new StartGame();
        controller.start(primaryStage);
    }

    // M2 TESTS

    @Test //Jay Qian
    public void testLabelOnScreen() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        Player player = new Player(320, 310, 70, 90, "testName", "Easy", "Sword");
        verifyThat("Money: " + player.getMoney(), NodeMatchers.isNotNull());
    }

    @Test //Jay Qian
    public void testImagesInDirectory() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        Image i1 = new Image("file:resources/dungeon-floor.jpg");
        assertNotNull(i1);
        Image i2 = new Image("file:resources/vertwall.jpeg");
        assertNotNull(i2);
        Image i3 = new Image("file:resources/horizwall.jpeg");
        assertNotNull(i3);
    }

    
    // M3 TESTS
    @Test // Andrew Mo
    public void testEnterNextRoom() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        type(KeyCode.A, 20);
        verifyThat("Money: 300", NodeMatchers.isNotNull());
    }

    @Test // Andrew Mo
    public void testGoingBack() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        type(KeyCode.D, 25);
        type(KeyCode.A, 10);
        verifyThat("Money: 300", NodeMatchers.isNotNull());
    }

    @Test //Shail Patel
    public void testPlayerExistence() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        verifyThat("#player", NodeMatchers.isNotNull());
    }

    @Test // Shail Patel
    public void testWallCollision() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        type(KeyCode.W, 4);
        type(KeyCode.A, 30);
        player = lookup("#player").query();
        assertEquals(player.getTranslateX(), 5, 1);
    }

    @Test // Rami Bouhafs
    public void testPlayerMovement() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.D, 10);
        type(KeyCode.W, 15);
        type(KeyCode.A, 5);
        assertEquals(player.getTranslateX(), 395, 1);
        assertEquals(player.getTranslateY(), 85, 1);
    }

    @Test // Rami Bouhafs
    public void testPlayerTraitsInNextRoom() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        type(KeyCode.TAB, 1);
        type(KeyCode.DOWN, 1);
        type(KeyCode.TAB, 1);
        type(KeyCode.DOWN, 1); //chooses "Medium" and "Axe"
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 21); //goes to next room
        assertSame(InitialConfigurationScreen.getChosenDifficulty(), "Medium");
        assertSame(InitialConfigurationScreen.getChosenStartingItem(), "Axe");
    }

    @Test // Jay Qian
    public void testMovementInNextRoom() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.D, 25);
        type(KeyCode.W, 15);
        assertEquals(player.getTranslateX(), 105, 1);
        assertEquals(player.getTranslateY(), 95, 1);
    }

    @Test // Jay Qian
    public void testMoneyDoesntChange() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        type(KeyCode.A, 21);
        verifyThat("Money: 300", NodeMatchers.isNotNull());
    }

    @Test // Jared Lawlor
    public void testMovementKeys() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.D, 1);
        assertEquals(player.getTranslateX(), 335, 1);
        type(KeyCode.W, 1);
        assertEquals(player.getTranslateY(), 295, 1);
        type(KeyCode.A, 1);
        assertEquals(player.getTranslateX(), 320, 1);
        type(KeyCode.S, 1);
        assertEquals(player.getTranslateY(), 310, 1);
    }

    @Test // Jared Lawlor
    public void testPositionGoingBack() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.D, 25);
        type(KeyCode.A, 10);
        assertEquals(player.getTranslateX(), 555, 1);
        assertEquals(player.getTranslateY(), 320, 1);
    }


    // M4 Tests

    @Test // Andrew Mo
    public void testRetreatToPreviousRoom() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.D, 25);
        type(KeyCode.A, 50);
        assertEquals(player.getTranslateX(), 10, 1);
        assertEquals(player.getTranslateY(), 320, 1);
    }

    @Test // Andrew Mo
    public void testLosingScreen() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        player.setTranslateX(monster.getTranslateX());
        player.setTranslateY(monster.getTranslateY());
        switch ((int) monster.getHeight()) {
        case 100:
            for (int i = 0; i < 15; i++) {
                type(KeyCode.A, 1);
                type(KeyCode.D, 1);
            }
            break;
        case 80:
            for (int i = 0; i < 30; i++) {
                type(KeyCode.A, 1);
                type(KeyCode.D, 1);
            }
            break;
        case 60:
            for (int i = 0; i < 150; i++) {
                type(KeyCode.A, 1);
                type(KeyCode.D, 1);
            }
            break;
        default:
            break;
        }
        verifyThat("You LOSE!", NodeMatchers.isNotNull());
    }

    @Test // Jared Lawlor
    public void testMonsterPresent() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        assertNotNull(monster);
    }

    @Test // Jared Lawlor
    public void testMonsterAttack() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        Label healthLabel = lookup("#health").query();
        int health = Integer.parseInt(healthLabel.getText().substring(4));
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        player.setTranslateX(monster.getTranslateX());
        player.setTranslateY(monster.getTranslateY());
        type(KeyCode.A);
        healthLabel = lookup("#health").query();
        int health2 = Integer.parseInt(healthLabel.getText().substring(4));
        assertTrue(health > health2);
    }

    @Test // Shail Patel
    public void testDisplayStatistics() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        player.setTranslateX(monster.getTranslateX());
        player.setTranslateY(monster.getTranslateY());
        switch ((int) monster.getHeight()) {
            case 100:
                for (int i = 0; i < 15; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 80:
                for (int i = 0; i < 30; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 60:
                for (int i = 0; i < 150; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            default:
                break;
        }
        verifyThat("Monsters Killed: 0\nDamage Dealt: 0\nDamage Taken: 300", NodeMatchers.isNotNull());
    }

    @Test // Shail Patel
    public void testPlayAgain() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        player.setTranslateX(monster.getTranslateX());
        player.setTranslateY(monster.getTranslateY());
        switch ((int) monster.getHeight()) {
            case 100:
                for (int i = 0; i < 15; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 80:
                for (int i = 0; i < 30; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 60:
                for (int i = 0; i < 150; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            default:
                break;
        }
        clickOn("Play Again");
        verifyThat("Difficulty:", NodeMatchers.isNotNull());
    }

    @Test // Rami Bouhafs
    public void testExitButtonExists() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        player.setTranslateX(monster.getTranslateX());
        player.setTranslateY(monster.getTranslateY());
        switch ((int) monster.getHeight()) {
            case 100:
                for (int i = 0; i < 15; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 80:
                for (int i = 0; i < 30; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            case 60:
                for (int i = 0; i < 150; i++) {
                    type(KeyCode.A, 1);
                    type(KeyCode.D, 1);
                }
                break;
            default:
                break;
        }
        verifyThat("Exit", NodeMatchers.isNotNull());
    }

    @Test // Rami Bouhafs
    public void testKillMonsters() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        player = lookup("#player").query();
        type(KeyCode.A, 25);
        monster = lookup("#monster").query();
        boolean monsterExists = true;
        while (monster != null) {
            player.setTranslateX(monster.getTranslateX());
            player.setTranslateY(monster.getTranslateY());
            type(KeyCode.SPACE);
            try {
                monster = lookup("#monster").query();
            } catch (EmptyNodeQueryException e) {
                System.out.println("here");
                monsterExists = false;
                break;
            }
        }
        if (monsterExists) {
            assertNotNull(null);
        } else {
            assertNull(null);
        }
    }

    @Test // Jay Qian
    public void testNoMonstersInFirstRoom() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        boolean monsterExists = true;
        try {
            monster = lookup("#monster").query();
        } catch (EmptyNodeQueryException e) {
            System.out.println("here");
            monsterExists = false;
        }
        if (monsterExists) {
            assertNotNull(null);
        } else {
            assertNull(null);
        }
    }

    @Test // Jay Qian
    public void testPlayerHPExists() {
        clickOn("Play");
        write("t");
        type(KeyCode.ENTER);
        clickOn("Continue");
        verifyThat("HP: 300", NodeMatchers.isNotNull());
    }
}