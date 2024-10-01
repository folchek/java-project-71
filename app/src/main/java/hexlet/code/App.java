package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import picocli.CommandLine;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable <Integer> {
    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        ObjectMapper objectMapper;
        if (filepath1.endsWith(".yaml") || filepath1.endsWith(".yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            objectMapper = new ObjectMapper();  // JSON по умолчанию
        }

        // Чтение и парсинг первого файла
        Map<String, Object> data1 = objectMapper.readValue(new File(filepath1), Map.class);

        // Чтение и парсинг второго файла
        Map<String, Object> data2 = objectMapper.readValue(new File(filepath2), Map.class);

        // Сравнение данных и вывод результатов
        System.out.println("Сравнение файлов:");
        System.out.println("Файл 1: " + data1);
        System.out.println("Файл 2: " + data2);

        return 0;
    }
}
