package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

@Getter
public class FloatStatistics extends TypeStatistics {
  private final double minVal;
  private final double maxVal;
  private final double average;

  private final String LONG_STATISTICS_STRING =
      "Minimal value: %.2f\nMaximum value: %.2f\nAverage: %.2f\n";

  public FloatStatistics(
      int elementCount, LineDataType type, double minVal, double maxVal, double average) {
    super(elementCount, type);
    this.minVal = minVal;
    this.maxVal = maxVal;
    this.average = average;
  }

  @Override
  public String getFullStatistics() {
    return getShortStatistics() + LONG_STATISTICS_STRING.formatted(minVal, maxVal, average);
  }
}
