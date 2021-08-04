import javafx.embed.swing.JFXPanel;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private JFXPanel panel = new JFXPanel();
    @BeforeEach
    void setUp() {
        player = new Player(320, 310, 70, 90, "Test", "Easy", "Sword");

    }

    @Test //Jared Lawlor
    void getDifficultyTest() {
        assertSame("Easy", player.getDifficulty());
        player = new Player(320, 310, 70, 90, "Test", "Hard", "Sword");
        assertSame("Hard", player.getDifficulty());
    }

    @Test //Jared Lawlor
    void getMoneyTest() {
        assertEquals(300, player.getMoney());
        player = new Player(320, 310, 70, 90, "Test", "Medium", "Sword");
        assertEquals(200, player.getMoney());
        player = new Player(320, 310, 70, 90, "Test", "Hard", "Sword");
        assertEquals(100, player.getMoney());
    }
}