package uniq;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;


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
    private String outputName;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inputName;


    public static void main(String[] args) {

        new UniqLauncher().launch(args);

    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {

            if (unique && replaced) {
                System.err.println("You can't use -u and -r together");
                return;
            }

            parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar uniq.jar -i -u -c -s num -o ofile file");
            parser.printUsage(System.err);
            return;
        }
        try {
            Uniq res = new Uniq(ignore, unique, replaced, num, outputName);
            res.stringList(inputName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
