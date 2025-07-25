package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;

@Getter
public class StringStatistics extends TypeStatistics {
  private final String LONG_STATISTICS_STRING =
      "Minimal line length: %d\nMaximum line length: %d\n";

  private final int minLength;
  private final int maxLength;

  public StringStatistics(int elementCount, LineDataType type, int minLength, int maxLength) {
    super(elementCount, type);
    this.minLength = minLength;
    this.maxLength = maxLength;
  }

  @Override
  public String getFullStatistics() {
    return getShortStatistics() + LONG_STATISTICS_STRING.formatted(minLength, maxLength);
  }
}
