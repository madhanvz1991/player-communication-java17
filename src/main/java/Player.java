import java.util.concurrent.BlockingQueue;

/**
 * Represents a player that can send and receive messages.
 * Responsibilities:
 * - Maintain its message counter.
 * - Process incoming messages and send responses asynchronously.
 */
public class Player {
    private final String name;
    private final BlockingQueue<String> incomingQueue;
    private final BlockingQueue<String> outgoingQueue;
    private static int messageCounter = 0;

    public Player(String name, BlockingQueue<String> incomingQueue, BlockingQueue<String> outgoingQueue) {
        this.name = name;
        this.incomingQueue = incomingQueue;
        this.outgoingQueue = outgoingQueue;
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    String message = incomingQueue.take();
                    messageCounter++;
                    String response = message + " (response #" + messageCounter + ")";
                    System.out.println(name + " received: " + message);
                    outgoingQueue.put(response);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(name + " stopped.");
                    break;
                }
            }
        }).start();
    }
}
