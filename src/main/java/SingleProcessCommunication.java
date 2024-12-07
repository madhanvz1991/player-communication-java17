import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Two players communicating within the same process.
 */
public class SingleProcessCommunication {
    public static void main(String[] args) {
        BlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();

        Player player1 = new Player("Player1", queue1, queue2);
        Player player2 = new Player("Player2", queue2, queue1);

        player1.start();
        player2.start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String message = "Message #" + i + " from Player1";
                    queue1.put(message);
                    System.out.println("Player1 sent: " + message);

                    String response = queue2.take();
                    System.out.println("Player1 received: " + response);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
