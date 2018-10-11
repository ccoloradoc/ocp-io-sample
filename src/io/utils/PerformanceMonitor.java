package io.utils;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;

public class PerformanceMonitor {
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();
	private Instant start = Instant.now();
	private Instant end;
	private String title;

	public PerformanceMonitor(String title) {
		this.title = title;
	}

	public PerformanceMonitor finish() {
		this.end = Instant.now();
		return this;
	}

	public PerformanceMonitor report() {
		Duration timelapse = Duration.between(this.start, this.end);
		System.out.format("Time [%s]: %sns\n", this.title, numberFormat.format(timelapse.getNano()));
		return this;
	}

}
