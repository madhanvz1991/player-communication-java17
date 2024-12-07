import java.io.*;
import java.nio.file.*;

/**
 * Two players in separate processes using file-based communication.
 */
public class MultiProcessCommunication {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path player1Input = Paths.get("player1.in");
        Path player2Input = Paths.get("player2.in");

        Files.deleteIfExists(player1Input);
        Files.deleteIfExists(player2Input);
        Files.createFile(player1Input);
        Files.createFile(player2Input);

        ProcessBuilder player1 = new ProcessBuilder(
                "java", "-cp", "target/classes", "PlayerProcess", "Player1", player1Input.toString(), player2Input.toString()
        );
        ProcessBuilder player2 = new ProcessBuilder(
                "java", "-cp", "target/classes", "PlayerProcess", "Player2", player2Input.toString(), player1Input.toString()
        );

        player1.redirectErrorStream(true);
        player2.redirectErrorStream(true);

        Process p1 = player1.start();
        Process p2 = player2.start();

        p1.waitFor();
        p2.waitFor();
    }
}
