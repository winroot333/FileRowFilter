package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Абстрактный класс для хранения базовой статистики по элементам определённого типа. */
@RequiredArgsConstructor
@Getter
public abstract class TypeStatistics {
  private final long elementCount;
  private final LineDataType type;
  private final String SHORT_STATISTICS_STRING = "Type: %s\nElement count: %d\n";

  /**
   * Возвращает полную статистику в виде форматированной строки.
   *
   * @return строка с полной статистикой
   */
  public abstract String getFullStatistics();

  /**
   * Возвращает краткую статистику (тип и количество элементов).
   *
   * @return строка с краткой статистикой
   */
  public String getShortStatistics() {
    return SHORT_STATISTICS_STRING.formatted(type.name(), elementCount);
  }
}
