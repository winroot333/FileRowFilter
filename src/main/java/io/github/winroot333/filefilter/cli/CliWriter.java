package io.github.winroot333.filefilter.cli;

import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CliWriter {
  public void error(String message) {
    System.err.println(message);
  }

  public void error(List<String> errors) {
    errors.forEach(System.err::println);
  }

  public void message(String message) {
    System.out.println(message);
  }

  public void message(List<String> errors) {
    errors.forEach(System.out::println);
  }
}
