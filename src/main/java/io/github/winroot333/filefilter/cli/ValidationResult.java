package io.github.winroot333.filefilter.cli;

/**
 * Результат валидации параметров командной строки Содержит сообщение об ошибке и признак
 * критичности ошибки
 *
 * @param message текст сообщения/ошибки валидации
 * @param isCritical флаг, указывающий на критичность ошибки (true - блокирующая ошибка)
 */
public record ValidationResult(String message, boolean isCritical) {}
