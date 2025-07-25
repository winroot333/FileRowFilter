package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

@Getter
public class IntStatistics extends TypeStatistics {
  private final long minVal;
  private final long maxVal;
  private final double average;

  private final String LONG_STATISTICS_STRING =
      "Minimal value: %d\nMaximum value: %d\nAverage: %.2f\n";

  public IntStatistics(
      int elementCount, LineDataType type, long minVal, long maxVal, double average) {
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
