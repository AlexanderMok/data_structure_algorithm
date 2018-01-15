package algorithm.exercise;

import java.time.Clock;

public class Stopwatch {
	private final long start;

	public Stopwatch() {
		start = Clock.systemUTC().millis();
	}

	public double elapsedTime() {
		long now = Clock.systemUTC().millis();
		return (now - start) / 1000.0;
	}

	public static void main(String[] args) throws InterruptedException {
		Stopwatch timer2 = new Stopwatch();
		double sum2 = 0.0;
		for (int i = 1; i <= 50; i++) {
			sum2 += Math.pow(i, 0.5);
		}
		Thread.sleep(1000);
		double time2 = timer2.elapsedTime();
		System.out.printf("%e (%.2f seconds)\n", sum2, time2);
	}
}
