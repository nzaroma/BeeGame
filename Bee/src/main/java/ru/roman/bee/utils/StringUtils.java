package ru.roman.bee.utils;

public class StringUtils {
	/**
	 * Build new string
	 * @param stringPart
	 * @return summ of stringPart
	 */
	public static String build(String... stringPart) {
		String finalString = "";
		for(String s: stringPart) {
			finalString += s;
		}
		return finalString;
	}
}
