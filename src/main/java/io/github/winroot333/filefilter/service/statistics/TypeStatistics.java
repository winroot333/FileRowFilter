package io.github.winroot333.filefilter.service.statistics;

import io.github.winroot333.filefilter.model.LineDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class TypeStatistics {
  private final long elementCount;
  private final LineDataType type;
  private final String SHORT_STATISTICS_STRING = "Type: %s\nElement count: %d\n";

  public abstract String getFullStatistics();

  public String getShortStatistics() {
    return SHORT_STATISTICS_STRING.formatted(type.name(), elementCount);
  }
}
