package de.tuhh.diss.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleIO {
	private static InputStreamReader converter = new InputStreamReader(System.in);
	private static BufferedReader in = new BufferedReader(converter);
	
	public static void println() {
		System.out.println();
	}
	
	public static void println(String val) {
		System.out.println(val);
	}
	
	public static void println(double val) {
		println(String.valueOf(val));
	}
	
	public static void println(float val) {
		println(String.valueOf(val));
	}
	
	public static void println(long val) {
		println(String.valueOf(val));
	}
	
	public static void println(int val) {
		println(String.valueOf(val));
	}
	
	public static void println(boolean val) {
		println(String.valueOf(val));
	}

	public static void println(Object val) {
		println(String.valueOf(val));
	}
	
	public static void print(String val) {
		System.out.print(val);
	}
	
	public static void print(double val) {
		print(String.valueOf(val));
	}
	
	public static void print(float val) {
		print(String.valueOf(val));
	}
	
	public static void print(long val) {
		print(String.valueOf(val));
	}
	
	public static void print(int val) {
		print(String.valueOf(val));
	}
	
	public static void print(boolean val) {
		print(String.valueOf(val));
	}

	public static void print(Object val) {
		print(String.valueOf(val));
	}

	public static double readDouble() {
		return Double.parseDouble(readString());
	}

	public static float readFloat() {
		return Float.parseFloat(readString());
	}

	public static int readInt() {
		return Integer.parseInt(readString());
	}
	
	public static int readInteger() {
		return readInt();
	}

	public static long readLong() {
		return Long.parseLong(readString());
	}

	public static boolean readBoolean() {
		String s = readString();
		if (s != null)
		{
			s = s.toLowerCase().trim();
			if (s.equals("true") || s.equals("yes"))
				return true;
			if (s.equals("false") || s.equals("no"))
				return false;
		}
		throw new NumberFormatException("Cannot parse boolean variable.");
	}

	public static boolean readBool() {
		return readBoolean();
	}
	
	public static String readString() {
		try {
			return in.readLine();
		} catch (IOException io) {
			throw new RuntimeException("Reading from stdin failed", io);
		}
	}
}
