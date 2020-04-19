import org.junit.Test;
import uniq.UniqLauncher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class test {


    private boolean outputFile(String output) throws IOException {
        return Files.readAllLines(Paths.get(output)).equals(Files.readAllLines(Paths.get(output)));
    }


    @Test
    public void testIR() throws IOException {
        String[] args = {"-i", "-r", "-o", "src/test/resources/outputIR.txt", "src/test/resources/testIR.txt"};
        String output = "src/test/resources/outputIR.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }


    @Test
    public void testU() throws IOException {
        String[] args = {"-u", "-o", "src/test/resources/outputU.txt", "src/test/resources/testU.txt"};
        String output = "src/test/resources/outputU.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }


    @Test
    public void testS() throws IOException {
        String[] args = {"-r", "-s", "2", "-o", "src/test/resources/outputS.txt", "src/test/resources/testS.txt"};
        String output = "src/test/resources/outputS.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }

}
