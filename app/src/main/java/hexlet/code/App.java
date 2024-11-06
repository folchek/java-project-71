package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/*@CommandLine.Command(name = "Differ",
        mixinStandardHelpOptions = true,
        version = "Differ 1.0",
        description = "Compares two configuration files and shows a difference.")*/
public class App/* implements Callable<Integer> */{
    /*@CommandLine.Parameters(index = "0", paramLabel = "filepath1",
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
    private String format = "stylish";*/

    public static void main(String[] args) throws IOException {
        /*int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);*/

        //тест метода Parser
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\bespa\\MyProjects\\java-project-71\\app\\file1.json")));
        System.out.println(Parser.parseString(content, "json"));

    }

   /* @Override
    public Integer call() throws Exception {
        return null;
    }*/
}
