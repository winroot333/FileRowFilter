package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

/** Класс статистики для float. Хранит минимальное, максимальное и среднее значения. */
@Getter
public class FloatStatistics extends TypeStatistics {
  private final double minVal;
  private final double maxVal;
  private final double average;

  private final String LONG_STATISTICS_STRING =
      "Minimal value: %.2f\nMaximum value: %.2f\nAverage: %.2f\n";

  /**
   * Конструктор для создания статистики float чисел
   *
   * @param elementCount количество элементов
   * @param minVal минимальное значение
   * @param maxVal максимальное значение
   * @param average среднее значение
   */
  public FloatStatistics(int elementCount, double minVal, double maxVal, double average) {
    super(elementCount, LineDataType.FLOAT);
    this.minVal = minVal;
    this.maxVal = maxVal;
    this.average = average;
  }

  @Override
  public String getFullStatistics() {
    return getShortStatistics() + LONG_STATISTICS_STRING.formatted(minVal, maxVal, average);
  }
}
