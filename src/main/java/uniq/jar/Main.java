package uniq.jar;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;

public class Main {

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignore case")
    private boolean ignore;

    @Option(name = "-u", metaVar = "Unique", usage = "Returns unique strings")
    private boolean unique;

    @Option(name = "-r", metaVar = "Replaced", usage = "Returns the number of the replaced strings")
    private boolean replaced;

    @Option(name = "-o", metaVar = "Output", usage = "Output file name")
    private String outputName;

    @Option(required = true, name = "file", metaVar = "Input", usage = "input file name")
    private String inputName;

    public static void main(String[] args) {
        new Main().launch(args);
    }

    private void launch(String[] args) {
        System.out.println(ignore + "sd");
    }
}