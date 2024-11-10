package hexlet.code;

import picocli.CommandLine;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Differ",
        mixinStandardHelpOptions = true,
        version = "Differ 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 15;
    @CommandLine.Parameters(index = "0", paramLabel = "filepath1",
            description = "path to the first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1", paramLabel = "filepath2",
            description = "path to the second file")
    private String filepath2;

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format: stylish, plain, json, "
                    + "no-format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            String result = Differ.generate(filepath1, filepath2, format);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }
}


