import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uniq.Uniq;
import uniq.UniqLauncher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class Tests {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setErr(new PrintStream(output));
    }


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

    @Test
    public void testErrUR() throws IOException {
        String[] args = {"-u", "-r", "-o", "src/test/resources/outputErr.txt", "src/test/resources/testErr.txt"};
        UniqLauncher.main(args);
        assertEquals("You can't use -u and -r together" + System.lineSeparator(),output.toString());
    }

    @Test
    public void testErrNegativeS() throws IOException {
        String[] args = {"-s", "-3", "-o", "src/test/resources/outputErr.txt", "src/test/resources/testErr.txt"};
        UniqLauncher.main(args);
        assertEquals("You can't slight negative number of symbols" + System.lineSeparator(),output.toString());
    }

    @Test
    public void testVoid() throws IOException {
        String[] args = {"-r", "-o", "src/test/resources/outputVoid.txt", "src/test/resources/testVoid.txt"};
        String output = "src/test/resources/outputVoid.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }

    @Test
    public void testUForRepeat() throws IOException {
        String[] args = {"-u", "-o", "src/test/resources/outputRepeat.txt", "src/test/resources/testRepeat.txt"};
        String output = "src/test/resources/outputRepeat.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }

    @Test
    public void testRForUnique() throws IOException {
        String[] args = {"-r", "-o", "src/test/resources/outputUnique.txt", "src/test/resources/testUnique.txt"};
        String output = "src/test/resources/outputUnique.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }

    @Test
    public void testNothing() throws IOException {
        String[] args = {"-o", "src/test/resources/outputNothing.txt", "src/test/resources/testNothing.txt"};
        String output = "src/test/resources/outputNothing.txt";
        UniqLauncher.main(args);
        assertTrue(outputFile(output));
    }

    @After
    public void cleanUpStrams() {
        System.setOut(null);
    }
}
