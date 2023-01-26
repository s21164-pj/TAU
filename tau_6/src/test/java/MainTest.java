import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testGenerateObstacles() {
        Main.generateObstacles();
        int obstaclesCount = 0;
        for (int x = 0; x < Main.BOARD_SIZE; x++) {
            for (int y = 0; y < Main.BOARD_SIZE; y++) {
                if (Main.board[x][y]) {
                    obstaclesCount++;
                }
            }
        }
        assertEquals(Main.OBSTACLES_COUNT, obstaclesCount);

        for (int x = 0; x < Main.BOARD_SIZE; x++) {
            for (int y = 0; y < Main.BOARD_SIZE; y++) {
                if (x == 0 && y == 0 || x == Main.BOARD_SIZE - 1 && y == Main.BOARD_SIZE - 1) {
                    assertFalse(Main.board[x][y]);
                }
            }
        }
    }

    @Test
    public void testMovePlayer() {
        Main.playerX = 0;
        Main.playerY = 0;
        Main.movePlayer("d");
        assertEquals(1, Main.playerX);
        assertEquals(0, Main.playerY);

        Main.movePlayer("s");
        assertEquals(1, Main.playerX);
        assertEquals(1, Main.playerY);
    }

    @Test
    public void testMovePlayerOutOfBounds() {
        Main.playerX = 0;
        Main.playerY = 0;
        Main.movePlayer("a");
        assertEquals(0, Main.playerX);
        assertEquals(0, Main.playerY);
    }

    @Test
    public void testCheckLose() {
        Main.board[0][0] = true;
        Main.playerX = 0;
        Main.playerY = 0;
        assertTrue(Main.checkLose());
    }

    @Test
    public void testCheckWin() {
        Main.playerX = Main.BOARD_SIZE - 1;
        Main.playerY = Main.BOARD_SIZE - 1;
        assertTrue(Main.checkWin());
    }

    @Test
    public void testGameOverAfterWin() {
        Main.playerX = 0;
        Main.playerY = 0;
        for (int i = 0; i < Main.BOARD_SIZE; i++) {
            Main.movePlayer("d");
            Main.movePlayer("s");
        }
        assertTrue(Main.checkWin());
    }

    @Test
    public void testGameOverAfterLose(){
        Main.board[2][2] = true;
        Main.playerX = 2;
        Main.playerY = 2;
        Main.movePlayer("d");
        assertTrue(Main.checkLose());
    }
}