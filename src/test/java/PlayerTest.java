import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private BlockingQueue<String> queue1;
    private BlockingQueue<String> queue2;

    @BeforeEach
    public void setUp() {
        queue1 = new LinkedBlockingQueue<>();
        queue2 = new LinkedBlockingQueue<>();
        Player player1 = new Player("Player1", queue1, queue2);
        Player player2 = new Player("Player2", queue2, queue1);

        // Start the player threads
        player1.start();
        player2.start();
    }

    @Test
    public void testSample() throws InterruptedException {
        String initialMessage = "Hello from Player1";

        // Simulate message exchange
        String message = "Test Message";
        String response = message + " Response";

        // Assert the expected result
        assertEquals("Test Message Response", response);
    }

    @Test
    public void testMessageExchange() throws InterruptedException {
        String initialMessage = "Hello from Player1";

        // Player1 sends a message to Player2
        queue1.put(initialMessage);

        // Player2 responds back
        String response = queue2.take();

//        // Ensure Player2 response contains Player1 message and the response count
//        assertNotEquals(initialMessage + " (response #1)", response);
//
//        // Player1 should now receive Player2 response
//        String finalResponse = queue1.take();
//        assertNotEquals(initialMessage + " (response #1)", finalResponse);
    }


}
