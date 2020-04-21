package uniq;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class UniqLauncher {

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignore case")
    private boolean ignore;

    @Option(name = "-u", metaVar = "Unique", usage = "Returns unique strings")
    private boolean unique;

    @Option(name = "-r", metaVar = "Replaced", usage = "Returns the number of the replaced strings")
    private boolean replaced;

    @Option(name = "-s", metaVar = "Slight", usage = "Slight first NUM symbols")
    private int num;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private Path outputName;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private Path inputName;


    public static void main(String[] args) throws IOException {

        new UniqLauncher().launch(args);

    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {

            parser.parseArgument(args);
            if (unique && replaced) {
                throw new IOException("You can't use -u and -r together");
            }

            if (num < 0) {
                throw new IOException("You can't slight negative number of symbols");
            }

        } catch (CmdLineException e) {

            System.err.println(e.getMessage());
            System.err.println("java -jar uniq.jar -i -u -r -s num -o ofile file");
            parser.printUsage(System.err);
            return;

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }


        ArrayList<String> inputStrings = new ArrayList<>();

        BufferedReader br = inputName != null ? Files.newBufferedReader(inputName) :
                new BufferedReader(new InputStreamReader(System.in));

        try {

            String str;

            while ((str = br.readLine()) != null) {
                inputStrings.add(str);
            }

            Uniq res = new Uniq(ignore, unique, replaced, num, outputName, inputStrings);
            res.output();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
