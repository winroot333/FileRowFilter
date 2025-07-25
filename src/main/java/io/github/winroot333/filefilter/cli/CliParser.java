package io.github.winroot333.filefilter.cli;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.apache.commons.cli.*;

@Getter
public class CliParser {
  private final Options options;
  private final HelpFormatter helpFormatter;
  private List<ValidationResult> validationResults;

  public CliParser() {
    options = CliOptions.buildOptions();
    helpFormatter = new HelpFormatter();
  }

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

  private List<ValidationResult> validateOptions(CommandLine cmd) {
    var result = new ArrayList<ValidationResult>();
    if (cmd.getArgList().isEmpty()) {
      result.add(new ValidationResult(CliErrorMessages.NO_INPUT_FILES, true));
    }

    if (cmd.hasOption("s") && cmd.hasOption("f")) {
      result.add(new ValidationResult(CliErrorMessages.BOTH_SHORT_LONG_STATS, false));
    }

    return result;
  }

  public boolean hasCriticalErrors() {
    return validationResults != null
        && validationResults.stream().anyMatch(ValidationResult::isCritical);
  }

  public void printHelp() {
    helpFormatter.printHelp("java -jar filter-util.jar", options);
  }
}
