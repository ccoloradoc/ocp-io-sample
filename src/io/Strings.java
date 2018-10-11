package io;

import io.utils.PerformanceMonitor;

import java.io.*;

public class Strings {
	public static void main(String... args) {
		readerWriter(new File("src/Main.java"), new File("cp/strings/rw/Main.java"));
		bufferedReadWriter(new File("src/Main.java"), new File("cp/strings/brw/Main.java"));
	}

	public static void readerWriter(File source, File destiny) {
		PerformanceMonitor monitor = new PerformanceMonitor("FileRW");
		new File(destiny.getParent()).mkdirs();

		try(Reader reader = new FileReader(source); Writer out = new FileWriter(destiny)) {
			int b;
			while((b = reader.read()) > 0) {
//				System.out.print(String.format("%c", b));
				out.write(b);
			}
			out.flush();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			monitor.finish().report();
		}
	}

	public static void bufferedReadWriter(File source, File destiny) {

		new File(destiny.getParent()).mkdirs();

		PerformanceMonitor monitor = new PerformanceMonitor("BufferedRW");

		try(BufferedReader in = new BufferedReader(new FileReader(source));
				BufferedWriter out = new BufferedWriter(new FileWriter(destiny))) {
			String line;
			while((line = in.readLine()) != null) {
//				System.out.println(line);
				out.write(line);
			}
			out.flush();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			monitor.finish().report();
		}
	}
}
