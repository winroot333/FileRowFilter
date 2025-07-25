package io.github.winroot333.filefilter.cli;

import java.util.List;
import lombok.experimental.UtilityClass;

/** Утилитарный класс для вывода сообщений в консоль. */
@UtilityClass
public class CliWriter {
  /**
   * Выводит сообщение об ошибке в stderr.
   *
   * @param message текст сообщения об ошибке
   */
  public void error(String message) {
    System.err.println(message);
  }

  /**
   * Выводит список сообщений об ошибках в stderr.
   *
   * @param errors список текстов ошибок
   */
  public void error(List<String> errors) {
    errors.forEach(System.err::println);
  }

  /**
   * Выводит информационное сообщение в stdout.
   *
   * @param message текст сообщения
   */
  public void message(String message) {
    System.out.println(message);
  }

  /**
   * Выводит список информационных сообщений в stdout.
   *
   * @param errors список текстов сообщений
   */
  public void message(List<String> errors) {
    errors.forEach(System.out::println);
  }
}
