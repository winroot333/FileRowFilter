package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

/**
 * Класс статистики для строковых значений. Содержит информацию о минимальной и максимальной длине
 * строк.
 */
@Getter
public class StringStatistics extends TypeStatistics {
  private final String LONG_STATISTICS_STRING =
      "Minimal line length: %d\nMaximum line length: %d\n";

  private final int minLength;
  private final int maxLength;

  /**
   * Конструктор для создания статистики строк
   *
   * @param elementCount количество строковых элементов
   * @param minLength минимальная длина строки
   * @param maxLength максимальная длина строки
   */
  public StringStatistics(int elementCount, int minLength, int maxLength) {
    super(elementCount, LineDataType.STRING);
    this.minLength = minLength;
    this.maxLength = maxLength;
  }

  @Override
  public String getFullStatistics() {
    return getShortStatistics() + LONG_STATISTICS_STRING.formatted(minLength, maxLength);
  }
}
