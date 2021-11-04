package fr.javaee.histogram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Histogram {
//	public static <T> Map<T, Integer> histogram(Stream<T> stream) {
//		return stream.collect(Collectors.groupingBy(Function.<T>identity(),
//				Collectors.mapping(i -> 1, Collectors.summingInt(s -> s.intValue()))));
//	}

	public static <T> Map<T, Long> histogram(Stream<T> stream) {
		return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	private static String readAllBytesJava7(String filePath) {
		String content = "";

		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public static void main(String[] a) {
		Map<Integer, Long> histo = histogram(new Random().ints(0, 10).limit(10000000).boxed());
		System.out.println(histo);

		String filePath = "pi-10million.txt";

		String dataLong = readAllBytesJava7(filePath);
		String[] sampleLongToken = dataLong.split("");
		System.out.println(sampleLongToken.length);
//		Map<String, Long> freqLong = Stream.of(sampleLongToken)
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map<String, Long> freqLong = histogram(Stream.of(sampleLongToken));
		System.out.println(freqLong);
	}
}