package io.github.winroot333.filefilter.model;

/**
 * Строка данных, обработанная строка из файла. Содержит тип строки и её значение.
 *
 * @param type тип данных строки
 * @param value исходное строковое значение в формате String
 */
public record LineData(LineDataType type, String value) {}
