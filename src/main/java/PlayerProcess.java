import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Handles MultiProcess communication via files for each player.
 */
public class PlayerProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        String playerName = args[0];
        Path inputFile = Paths.get(args[1]);
        Path outputFile = Paths.get(args[2]);

        int counter = 0;

        while (counter < 10) {
            try (var reader = Files.newBufferedReader(inputFile);
                 var writer = Files.newBufferedWriter(outputFile, StandardOpenOption.APPEND)) {

                String received = reader.readLine();
                if (received != null) {
                    counter++;
                    String response = received + " (response #" + counter + ")";
                    System.out.println(playerName + " received: " + received);
                    writer.write(response);
                    writer.newLine();
                }
            }

            TimeUnit.MILLISECONDS.sleep(100); // Simulate processing delay
        }
    }
}
