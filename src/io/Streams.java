package io;

import io.utils.PerformanceMonitor;

import java.io.*;
import java.text.NumberFormat;

public class Streams {

	private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();

	public static void main(String... args) {
		inputStreamCopy(new File("src/Main.java"), new File("cp/stream/bytes/Main.java"));
		bufferedStreamCopy(new File("src/Main.java"), new File("cp/stream/buffered/Main.java"));
		streamReaderCopy(new File("src/Main.java"), new File("cp/stream/bridge/Main.java"));
	}

	public static void streamReaderCopy(File source, File destiny) {
		if(!source.exists()) throw new IllegalArgumentException("Source file must exist");

		new File(destiny.getParent()).mkdirs();

		PerformanceMonitor monitor = new PerformanceMonitor("Bridge");

		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destiny)))) {

				String line;

				while((line = in.readLine()) != null) {
					out.write(line + "\n");
				}
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		monitor.finish().report();
	}


	public static void bufferedStreamCopy(File source, File destiny) {
		if(!source.exists()) throw new IllegalArgumentException("Source file must exist");

		new File(destiny.getParent()).mkdirs();

		PerformanceMonitor monitor = new PerformanceMonitor("BufferedIO");

		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destiny))) {

			byte[] buffer = new byte[1024];
			int bytesRead;
			while((bytesRead = in.read(buffer)) > 0) {
//				for(int i = 0; i < bytesRead ; i++) {
//					byte b = buffer[i];
//					System.out.print(String.format("%c", b));
//				}

				out.write(buffer, 0, bytesRead);
				out.flush();
			}

		} catch (IOException  e) {
			e.printStackTrace();
		}

		monitor.finish().report();
	}

	public static void inputStreamCopy(File source, File destiny) {
		if(!source.exists()) throw new IllegalArgumentException("Source file must exist");

		new File(destiny.getParent()).mkdirs();

		PerformanceMonitor monitor = new PerformanceMonitor("IOStream");

		new File(destiny.getParent()).mkdirs();

		try(InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destiny)) {
			int b;
			while((b = in.read()) > 0) {
//				System.out.print(String.format("%c", b));
				out.write(b);
			}

			out.flush();
		} catch (IOException  e) {
			e.printStackTrace();
		}

		monitor.finish().report();
	}
}
