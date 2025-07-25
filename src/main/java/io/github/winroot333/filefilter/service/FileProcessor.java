package io.github.winroot333.filefilter.service;

import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Cleanup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
  public static List<LineData> processFile(String filePath) throws IOException {
    List<LineData> data = new ArrayList<>();
    File file = new File(filePath);
    @Cleanup Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      data.add(processLine(line));
    }
    return data;
  }

  private static LineData processLine(String line) {
    try {
      Long.parseLong(line);
      return new LineData(LineDataType.INTEGER, line);
    } catch (NumberFormatException e1) {
      try {
        Double.parseDouble(line);
        return new LineData(LineDataType.FLOAT, line);
      } catch (NumberFormatException e2) {
        return new LineData(LineDataType.STRING, line);
      }
    }
  }
}
