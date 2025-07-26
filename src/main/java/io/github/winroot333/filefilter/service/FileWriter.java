package io.github.winroot333.filefilter.service;

import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.model.LineDataType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

/** Класс для записи данных в файлы с разделением по типам данных */
@Builder
@RequiredArgsConstructor
public class FileWriter {
  private final String outputPath;
  private final String filePrefix;
  private final boolean appendMode;

  private final String INTEGERS_FILE_NAME = "integers.txt";
  private final String FLOATS_FILE_NAME = "floats.txt";
  private final String STRINGS_FILE_NAME = "strings.txt";

  /**
   * Записывает данные в соответствующие файлы по типам.
   *
   * @param data список данных для записи
   * @return список ошибок List
   */
  public List<String> writeData(List<LineData> data) {
    List<String> errors = new ArrayList<>();
    StandardOpenOption[] options =
        appendMode
            ? new StandardOpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND}
            : new StandardOpenOption[] {
              StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            };

    try {
      writeFile(data, LineDataType.INTEGER, INTEGERS_FILE_NAME, options);
    } catch (IOException e) {
      errors.add(e.getMessage());
    }

    try {
      writeFile(data, LineDataType.FLOAT, FLOATS_FILE_NAME, options);
    } catch (IOException e) {
      errors.add(e.getMessage());
    }

    try {
      writeFile(data, LineDataType.STRING, STRINGS_FILE_NAME, options);
    } catch (IOException e) {
      errors.add(e.getMessage());
    }

    return errors;
  }

  /**
   * Записывает данные конкретного типа в указанный файл.
   *
   * @param data список всех данных
   * @param dataType тип данных для фильтрации
   * @param fileName имя файла для записи
   * @param options опции открытия файла
   * @throws IOException при ошибках записи
   */
  private void writeFile(
      List<LineData> data, LineDataType dataType, String fileName, StandardOpenOption[] options)
      throws IOException {
    var lines =
        data.stream().filter(line -> line.type().equals(dataType)).map(LineData::value).toList();

    Path filePath = Path.of(outputPath, filePrefix + fileName);

    if (lines.isEmpty()) {
      if (!appendMode) {
        deleteFileIfExists(filePath);
      }
    } else {
      Files.createDirectories(filePath.getParent());
      Files.write(filePath, lines, options);
    }
  }

  /**
   * Удаляет файл, если он существует
   *
   * @param filePath путь к файлу для удаления
   * @throws IOException при ошибках удаления
   */
  private void deleteFileIfExists(Path filePath) throws IOException {
    if (Files.exists(filePath)) {
      Files.delete(filePath);
    }
  }
}
