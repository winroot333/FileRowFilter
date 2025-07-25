package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineData;
import io.github.winroot333.filefilter.model.LineDataType;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Сервис для сбора и формирования статистики по обработанным данным. */
@RequiredArgsConstructor
@Getter
public class StatisticsService {
  private final boolean fullStatistics;

  private List<LineData> lineData = new ArrayList<>();

  private List<TypeStatistics> typeStatistics = new ArrayList<>();

  /**
   * Добавляет новые данные для статистики и пересчитывает её.
   *
   * @param lineData список данных для добавления
   */
  public void addData(List<LineData> lineData) {
    this.lineData.addAll(lineData);
    this.calculateStatistics();
  }

  /**
   * Возвращает статистику в полном или кратком формате.
   *
   * @return отформатированная строка со статистикой
   */
  public String getStatistics() {
    if (fullStatistics) {
      return typeStatistics.stream()
          .map(TypeStatistics::getFullStatistics)
          .collect(Collectors.joining());
    } else
      return typeStatistics.stream()
          .map(TypeStatistics::getShortStatistics)
          .collect(Collectors.joining());
  }

  /** Вычисляет статистику по всем типам данных. */
  private void calculateStatistics() {
    typeStatistics.clear();

    var stringLines = getElementsByType(LineDataType.STRING);
    int stringCount = stringLines.size();
    if (!stringLines.isEmpty()) {
      int stringMinVal = stringLines.stream().mapToInt(String::length).min().getAsInt();
      int stringMaxVal = stringLines.stream().mapToInt(String::length).max().getAsInt();
      typeStatistics.add(new StringStatistics(stringCount, stringMinVal, stringMaxVal));
    }

    var intLines = getElementsByType(LineDataType.INTEGER);
    int intCount = stringLines.size();
    if (!intLines.isEmpty()) {
      long intMinVal = intLines.stream().mapToLong(Long::valueOf).min().getAsLong();
      long intMaxVal = intLines.stream().mapToLong(Long::valueOf).max().getAsLong();
      double intAvg = intLines.stream().mapToDouble(Long::valueOf).average().getAsDouble();
      typeStatistics.add(new IntStatistics(intCount, intMinVal, intMaxVal, intAvg));
    }

    var floatLines = getElementsByType(LineDataType.FLOAT);
    int floatCount = floatLines.size();
    if (!floatLines.isEmpty()) {
      double floatMinVal = floatLines.stream().mapToDouble(Float::valueOf).min().getAsDouble();
      double floatMaxVal = floatLines.stream().mapToDouble(Float::valueOf).max().getAsDouble();
      double floatAvg = floatLines.stream().mapToDouble(Float::valueOf).average().getAsDouble();
      typeStatistics.add(new FloatStatistics(floatCount, floatMinVal, floatMaxVal, floatAvg));
    }
  }

  /**
   * Возвращает список значений определённого типа.
   *
   * @param type тип данных для фильтрации
   * @return список строковых значений
   */
  private List<String> getElementsByType(LineDataType type) {
    return lineData.stream().filter(l -> l.type().equals(type)).map(LineData::value).toList();
  }
}
