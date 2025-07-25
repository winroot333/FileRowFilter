package io.github.winroot333.filefilter.cli;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Класс параметров приложения. Хранит настройки полученные из аргументов командной строки */
@Setter
@Getter
public class ApplicationOptions {

  private String outputPath = ".";
  private String filePrefix = "";
  private boolean appendMode = false;
  private boolean fullStatistics = false;
  private List<String> inputFiles;
}
