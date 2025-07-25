package io.github.winroot333.filefilter;

import io.github.winroot333.filefilter.cli.ApplicationOptions;
import io.github.winroot333.filefilter.cli.CliParser;
import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.service.FileProcessor;
import io.github.winroot333.filefilter.service.FileWriter;
import io.github.winroot333.filefilter.service.statistics.StatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.ParseException;

public class ApplicationRunner {
  public static void main(String[] args) {
    try {
      CliParser cliParser = new CliParser();
      ApplicationOptions options = cliParser.parse(args);

      cliParser.getValidationResults().forEach(e -> System.out.println(e.message()));

      if (cliParser.hasCriticalErrors()) {
        System.out.println("Critical errors found, cannot continue execution");
        System.exit(1);
      }

      List<String> errors = new ArrayList<>();
      var lines = new ArrayList<LineData>();
      for (String file : options.getInputFiles()) {
        try {
          lines.addAll(FileProcessor.processFile(file));
        } catch (Exception e) {
          errors.add(e.getMessage());
        }
      }

      var writer =
          FileWriter.builder()
              .outputPath(options.getOutputPath())
              .filePrefix(options.getFilePrefix())
              .appendMode(options.isAppendMode())
              .build();

      errors.addAll(writer.writeData(lines));

      var statisticsService = new StatisticsService(options.isFullStatistics());
      statisticsService.addData(lines);
      System.out.println(statisticsService.getStatistics());

      errors.forEach(System.err::println);

    } catch (ParseException e) {
      System.err.println("Critical parse error: " + e.getMessage());
      new CliParser().printHelp();
    } catch (Exception e) {
      System.err.println("Critical error: " + e.getMessage());
    }
  }
}
