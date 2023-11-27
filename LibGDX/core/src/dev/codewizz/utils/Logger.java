package dev.codewizz.utils;

import java.time.LocalTime;

public class Logger {

	public static void error(String message) {
		Thread t = Thread.currentThread();
		LocalTime time = LocalTime.now();
		String s = t.getStackTrace()[2].getFileName().substring(0, t.getStackTrace()[2].getFileName().length()-5);

		String hour = (time.getHour() <= 9 ? "0" + time.getHour() : "" + time.getHour());
		String min = (time.getMinute() <= 9 ? "0" + time.getMinute() : "" + time.getMinute());
		String sec = (time.getSecond() <= 9 ? "0" + time.getSecond() : "" + time.getSecond());
		
		System.err.println("[" + hour + ":" + min + ":" + sec + "] [" + t.getName() + ":" + s + ":" + t.getStackTrace()[2].getLineNumber() + "] [ERROR]: " + message);
	}
	
	public static void log(String message) {
		Thread t = Thread.currentThread();
		LocalTime time = LocalTime.now();
		String s = t.getStackTrace()[2].getFileName().substring(0, t.getStackTrace()[2].getFileName().length()-5);
		
		String hour = (time.getHour() <= 9 ? "0" + time.getHour() : "" + time.getHour());
		String min = (time.getMinute() <= 9 ? "0" + time.getMinute() : "" + time.getMinute());
		String sec = (time.getSecond() <= 9 ? "0" + time.getSecond() : "" + time.getSecond());
		
		System.out.println("[" + hour + ":" + min + ":" + sec + "] [" + t.getName() + ":" + s + ":" + t.getStackTrace()[2].getLineNumber() + "] [INFO]: " + message);
	}
	
	public static void warn(String message) {
		Thread t = Thread.currentThread();
		LocalTime time = LocalTime.now();
		String s = t.getStackTrace()[2].getFileName().substring(0, t.getStackTrace()[2].getFileName().length()-5);
		
		String hour = (time.getHour() <= 9 ? "0" + time.getHour() : "" + time.getHour());
		String min = (time.getMinute() <= 9 ? "0" + time.getMinute() : "" + time.getMinute());
		String sec = (time.getSecond() <= 9 ? "0" + time.getSecond() : "" + time.getSecond());
		
		System.err.println("[" + hour + ":" + min + ":" + sec + "] [" + t.getName() + ":" + s + ":" + t.getStackTrace()[2].getLineNumber() + "] [WARN]: " + message);
	}
}
