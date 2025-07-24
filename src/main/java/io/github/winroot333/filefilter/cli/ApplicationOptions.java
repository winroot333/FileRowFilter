package io.github.winroot333.filefilter.cli;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationOptions {

    private String outputPath = ".";
    private String filePrefix = "";
    private boolean appendMode = false;
    private boolean fullStatistics = false;
    private List<String> inputFiles;

}
