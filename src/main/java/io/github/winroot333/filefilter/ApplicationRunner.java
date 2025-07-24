package io.github.winroot333.filefilter;

import io.github.winroot333.filefilter.cli.ApplicationOptions;
import io.github.winroot333.filefilter.cli.CliParser;
import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.model.LineDataType;
import io.github.winroot333.filefilter.service.FileProcessor;
import io.github.winroot333.filefilter.service.StatisticsService;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
      var rows = new ArrayList<LineData>();
      for (String file : options.getInputFiles()){
        try {
          rows.addAll(FileProcessor.processFile(file));
        } catch (Exception e){
          errors.add(e.getMessage());
        }
      }

      errors.forEach(System.err::println);

    } catch (ParseException e) {
      System.err.println("Critical parse error: " + e.getMessage());
      new CliParser().printHelp();
    } catch (Exception e) {
      System.err.println("Critical error: " + e.getMessage());
    }
  }
}
