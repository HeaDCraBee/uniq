package uniq;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;

import java.io.*;
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


    ArrayList<String> strings = new ArrayList<>();
    ArrayList<Integer> numbers = new ArrayList<>();


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

            Uniq res = new Uniq(ignore, unique, replaced, num, inputStrings, strings, numbers);
            res.ret();
            output();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void output() throws IOException {

        BufferedWriter writer = outputName != null ? Files.newBufferedWriter(outputName) :
                new BufferedWriter(new OutputStreamWriter(System.out));

        if (strings.size() == 0)
            writer.write("");
        else for (int i = 0; i < strings.size(); i++) {
            if (replaced) {
                if (numbers.get(i) == 1)
                    writer.write(strings.get(i));
                else
                    writer.write(numbers.get(i) + " " + strings.get(i));
            } else
                writer.write(strings.get(i));
            writer.newLine();

        }
        writer.close();
    }
}

