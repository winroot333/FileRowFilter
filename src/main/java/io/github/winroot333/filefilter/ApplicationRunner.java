package io.github.winroot333.filefilter;

import io.github.winroot333.filefilter.cli.ApplicationOptions;
import io.github.winroot333.filefilter.cli.CliParser;
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

    } catch (ParseException e) {
      System.err.println("Critical parse error: " + e.getMessage());
      new CliParser().printHelp();
    } catch (Exception e) {
      System.err.println("Critical error: " + e.getMessage());
    }
  }
}
