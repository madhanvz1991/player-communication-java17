import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleProcessCommunicationTest {

    @Test
    public void testCommunication() throws InterruptedException {
        BlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();

        Player player1 = new Player("Player1", queue1, queue2);
        Player player2 = new Player("Player2", queue2, queue1);

        // Start players in separate threads
        player1.start();
        player2.start();

        // Test sending and receiving 10 messages
//        for (int i = 1; i <= 10; i++) {
//            String message = "Message #" + i + " from Player1";
//            queue1.put(message);
//
//            // Player1 sends a message and receives a response
//            String response = queue2.take();
//            assertEquals(message + " (response #" + i + ")", response);
//
//            // Player1 should receive Player2 response back
//            String finalResponse = queue1.take();
//            assertEquals(message + " (response #" + i + ")", finalResponse);
//        }
    }
}
