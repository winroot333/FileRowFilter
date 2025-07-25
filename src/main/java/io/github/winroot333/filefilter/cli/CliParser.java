package io.github.winroot333.filefilter.cli;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.apache.commons.cli.*;

/**
 * Парсер аргументов командной строки для утилиты фильтрации файлов. Обрабатывает входные параметры
 * и выполняет их валидацию.
 */
@Getter
public class CliParser {
  public static final String HELP_COMMAND_EXAMPLE =
      "java -jar file-row-filter.jar -o testfile_1.txt";
  public static final String BOTH_SHORT_AND_FULL_STATISTICS_ERROR =
      "Both short and full statistics selected, full statistics will be used";
  public static final String NO_INPUT_FILES_ERROR = "No input files specified";
  private final Options options;
  private final HelpFormatter helpFormatter;
  private List<ValidationResult> validationResults;

  /** Конструктор инициализирует доступные опции командной строки. */
  public CliParser() {
    options = CliOptions.buildOptions();
    helpFormatter = new HelpFormatter();
  }

  /**
   * Парсит аргументы командной строки и создает объект настроек приложения
   *
   * @param args аргументы командной строки
   * @return объект ApplicationOptions или null при запросе помощи (-h) или ошибках
   * @throws ParseException при ошибках разбора аргументов
   */
  public ApplicationOptions parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    // todo переделать обработку при выводе help
    if (cmd.hasOption("h")) {
      return null;
    }

    this.validationResults = validateOptions(cmd);

    ApplicationOptions appOptions = new ApplicationOptions();
    appOptions.setOutputPath(cmd.getOptionValue("o", "."));
    appOptions.setFilePrefix(cmd.getOptionValue("p", ""));
    appOptions.setAppendMode(cmd.hasOption("a"));
    appOptions.setFullStatistics(cmd.hasOption("f"));
    appOptions.setInputFiles(cmd.getArgList());

    return appOptions;
  }

  /**
   * Проверяет корректность комбинаций опций
   *
   * @param cmd распарсенные аргументы командной строки
   * @return список результатов валидации
   */
  private List<ValidationResult> validateOptions(CommandLine cmd) {
    var result = new ArrayList<ValidationResult>();
    if (cmd.getArgList().isEmpty()) {
      result.add(new ValidationResult(NO_INPUT_FILES_ERROR, true));
    }

    if (cmd.hasOption("s") && cmd.hasOption("f")) {
      result.add(new ValidationResult(BOTH_SHORT_AND_FULL_STATISTICS_ERROR, false));
    }
    return result;
  }

  /**
   * Проверяет наличие критических ошибок валидации и продолжение работы невозможно
   *
   * @return true если есть критические ошибки
   */
  public boolean hasCriticalErrors() {
    return validationResults != null
        && validationResults.stream().anyMatch(ValidationResult::isCritical);
  }

  /** Выводит справочную информацию по использованию утилиты. */
  public void printHelp() {
    helpFormatter.printHelp(HELP_COMMAND_EXAMPLE, options);
  }
}
