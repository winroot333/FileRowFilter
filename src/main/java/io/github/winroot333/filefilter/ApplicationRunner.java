package io.github.winroot333.filefilter;

import io.github.winroot333.filefilter.cli.ApplicationOptions;
import io.github.winroot333.filefilter.cli.CliParser;
import io.github.winroot333.filefilter.cli.CliWriter;
import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.service.FileProcessor;
import io.github.winroot333.filefilter.service.FileWriter;
import io.github.winroot333.filefilter.service.statistics.StatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.ParseException;

/** Главный класс приложения для обработки файлов */
public class ApplicationRunner {
  /**
   * Точка входа в приложение. Обрабатывает исключения верхнего уровня.
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    try {
      runApplication(args);
    } catch (Exception e) {
      CliWriter.error("Unexpected error: " + e.getMessage());
    }
  }

  /**
   * Основной метод выполнения логики приложения.
   *
   * @param args аргументы командной строки
   * @throws Exception при возникновении ошибок обработки
   */
  private static void runApplication(String[] args) throws Exception {
    ApplicationOptions options = parseCommandLineArguments(args);
    if (options == null) {
      new CliParser().printHelp();
      System.exit(1);
    }

    List<LineData> lines = processInputFiles(options.getInputFiles());
    writeOutputFiles(options, lines);
    printStatistics(options, lines);
  }

  /**
   * Парсит и валидирует аргументы командной строки.
   *
   * @param args аргументы командной строки
   * @return объект с параметрами приложения или null при ошибках или запросе помощи help
   */
  private static ApplicationOptions parseCommandLineArguments(String[] args) {
    try {
      CliParser cliParser = new CliParser();
      ApplicationOptions options = cliParser.parse(args);

      if (options == null) {
        return null;
      }

      cliParser.getValidationResults().forEach(e -> CliWriter.message(e.message()));
      if (cliParser.hasCriticalErrors()) {
        CliWriter.error("Critical errors found, cannot continue execution");
        return null;
      }
      return options;

    } catch (ParseException e) {
      CliWriter.error("Critical argument parse error: " + e.getMessage());
      return null;
    }
  }

  /**
   * Обрабатывает входные файлы и собирает данные
   *
   * @param inputFiles список путей к входным файлам
   * @return список обработанных строк данных
   */
  private static List<LineData> processInputFiles(List<String> inputFiles) {
    List<String> errors = new ArrayList<>();
    var lines = new ArrayList<LineData>();
    for (String file : inputFiles) {
      try {
        lines.addAll(FileProcessor.processFile(file));
      } catch (Exception e) {
        errors.add(e.getMessage());
      }
    }
    CliWriter.error(errors);
    return lines;
  }

  /**
   * Записывает результаты обработки в выходные файлы
   *
   * @param options параметры приложения
   * @param lines данные для записи
   */
  private static void writeOutputFiles(ApplicationOptions options, List<LineData> lines) {
    var writer =
        FileWriter.builder()
            .outputPath(options.getOutputPath())
            .filePrefix(options.getFilePrefix())
            .appendMode(options.isAppendMode())
            .build();
    var errors = writer.writeData(lines);
    CliWriter.error(errors);
  }

  /**
   * Формирует и выводит статистику по обработанным данным
   *
   * @param options параметры приложения
   * @param lines данные для анализа
   */
  private static void printStatistics(ApplicationOptions options, List<LineData> lines) {
    var statisticsService = new StatisticsService(options.isFullStatistics());
    statisticsService.addData(lines);
    CliWriter.message(statisticsService.getStatistics());
  }
}
