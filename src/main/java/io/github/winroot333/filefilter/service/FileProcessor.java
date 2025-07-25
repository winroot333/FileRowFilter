package io.github.winroot333.filefilter.service;

import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.model.LineDataType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Cleanup;

/** Класс для обработки файлов и определения типов данных в строках. */
public class FileProcessor {
  /**
   * Обрабатывает файл построчно и определяет тип каждой строки.
   *
   * @param filePath путь к обрабатываемому файлу
   * @return список объектов LineData с определёнными типами
   * @throws IOException при ошибках чтения файла
   */
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

  /**
   * Определяет тип данных в строке (целое число, дробное число или строка).
   *
   * @param line обрабатываемая строка
   * @return объект LineData с определённым типом и значением
   */
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
