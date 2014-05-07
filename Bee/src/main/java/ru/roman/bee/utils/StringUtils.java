package ru.roman.bee.utils;

public class StringUtils {
	/**
	 * Build new string
	 * @param stringPart
	 * @return summ of stringPart
	 */
	public static String build(String... stringPart) {
		StringBuilder stringBuilder = new StringBuilder(256);
		for(String part: stringPart) {
			stringBuilder.append(part);
		}		
		return stringBuilder.toString();
	}
	
	public static String build(Object... objects) {
		StringBuilder stringBuilder = new StringBuilder(256);
		for(Object part: objects) {
			stringBuilder.append(part);
		}		
		return stringBuilder.toString();
	}
}
