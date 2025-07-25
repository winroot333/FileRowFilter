package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

/**
 * Класс статистики для целочисленных значений. Хранит минимальное, максимальное и среднее значения.
 */
@Getter
public class IntStatistics extends TypeStatistics {
  private final long minVal;
  private final long maxVal;
  private final double average;

  private final String LONG_STATISTICS_STRING =
      "Minimal value: %d\nMaximum value: %d\nAverage: %.2f\n";

  /**
   * Конструктор для статистики целых чисел
   *
   * @param elementCount количество элементов
   * @param minVal минимальное значение
   * @param maxVal максимальное значение
   * @param average среднее значение
   */
  public IntStatistics(int elementCount, long minVal, long maxVal, double average) {
    super(elementCount, LineDataType.INTEGER);
    this.minVal = minVal;
    this.maxVal = maxVal;
    this.average = average;
  }

  @Override
  public String getFullStatistics() {
    return getShortStatistics() + LONG_STATISTICS_STRING.formatted(minVal, maxVal, average);
  }
}
