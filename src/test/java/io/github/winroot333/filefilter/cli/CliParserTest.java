package io.github.winroot333.filefilter.cli;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CliParserTest {

  private CliParser cliParser;

  @BeforeEach
  void setUp() {
    cliParser = new CliParser();
  }

  @Test
  @DisplayName("Должен возвращать null при наличии опции -h")
  void shouldReturnNullForHelpOption() throws ParseException {
    String[] args = {"-h"};
    ApplicationOptions result = cliParser.parse(args);
    assertThat(result).isNull();
  }

  @Test
  @DisplayName("Должен устанавливать значения по умолчанию при минимальных аргументах")
  void shouldSetDefaultValuesForMinimalArgs() throws ParseException {
    String[] args = {"input.txt"};
    ApplicationOptions result = cliParser.parse(args);

    assertThat(result.getOutputPath()).isEqualTo(".");
    assertThat(result.getFilePrefix()).isEmpty();
    assertThat(result.isAppendMode()).isFalse();
    assertThat(result.isFullStatistics()).isFalse();
    assertThat(result.getInputFiles()).containsExactly("input.txt");
  }

  @Test
  @DisplayName("Должен обрабатывать все опции")
  void shouldHandleAllOptions() throws ParseException {
    String[] args = {"-o", "out", "-p", "pref", "-a", "-f", "file1", "file2"};
    ApplicationOptions result = cliParser.parse(args);

    assertThat(result.getOutputPath()).isEqualTo("out");
    assertThat(result.getFilePrefix()).isEqualTo("pref");
    assertThat(result.isAppendMode()).isTrue();
    assertThat(result.isFullStatistics()).isTrue();
    assertThat(result.getInputFiles()).containsExactly("file1", "file2");
  }

  @Test
  @Disabled
  @DisplayName("Должен добавлять некритическую ошибку при неизвестных аргументах")
  void shouldAddNonCriticalErrorForUnknownArgs() throws ParseException {
    String[] args = {"-unknown", "file.txt"};
    ApplicationOptions result = cliParser.parse(args);

    assertThat(result).isNotNull();
    assertThat(cliParser.getValidationResults())
        .anyMatch(r -> r.message().contains("Unknown option"));
  }

  @Test
  @DisplayName("Должен отмечать критическую ошибку при отсутствии входных файлов")
  void shouldMarkCriticalErrorWhenNoInputFiles() throws ParseException {
    String[] args = {};
    cliParser.parse(args);
    assertThat(cliParser.hasCriticalErrors()).isTrue();
  }

  @Test
  @DisplayName("Должен отмечать некритическую ошибку при конфликте опций статистики")
  void shouldMarkNonCriticalErrorForStatsConflict() throws ParseException {
    String[] args = {"-s", "-f", "file.txt"};
    cliParser.parse(args);

    assertThat(cliParser.getValidationResults()).allMatch(r -> !r.isCritical());
  }
}
