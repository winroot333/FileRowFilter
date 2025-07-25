package io.github.winroot333.filefilter.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CliOptions {
  public static Options buildOptions() {
    Options options = new Options();

    options.addOption(
        Option.builder("o")
            .longOpt("output")
            .hasArg()
            .argName("path")
            .desc("Output directory path")
            .build());

    options.addOption(
        Option.builder("p")
            .longOpt("prefix")
            .hasArg()
            .argName("prefix")
            .desc("Prefix for output files")
            .build());

    options.addOption(
        Option.builder("a")
                .longOpt("append")
                .desc("Append to existing files")
                .build());

    options.addOption(
        Option.builder("s")
                .longOpt("short-stats")
                .desc("Show short statistics")
                .build());

    options.addOption(
        Option.builder("f")
                .longOpt("full-stats")
                .desc("Show full statistics")
                .build());

    options.addOption(Option.builder("h")
            .longOpt("help")
            .desc("Show help")
            .build());

    return options;
  }
}
