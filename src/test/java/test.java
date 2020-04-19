import org.junit.Test;
import uniq.UniqLauncher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class test {

    private final String[] args = {"-i","-r","-o", "src/test/resources/output.txt", "src/test/resources/input.txt" };

    private boolean outputFile(String output) throws IOException {
        return Files.readAllLines(Paths.get(output)).equals(Files.readAllLines(Paths.get("src/test/resources/output.txt")));
    }

    @Test
    public void testing() throws IOException {
        String output = "src/test/resources/output.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }
}
