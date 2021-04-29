import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'organizingContainers' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * 2D_INTEGER_ARRAY container as parameter.
	 */

	public static String organizingContainers(List<List<Integer>> container) {
		// Write your code here

		int ballTypes = container.get(0).size();

		List<Integer> capacities = new ArrayList<>();
		List<Integer> ballTotals = new ArrayList<>();

		for (int bucket = 0; bucket < container.size(); bucket++) {

			int capacity = 0;
			int ballTotal = 0;

			for (int ballType = 0; ballType < container.get(bucket).size(); ballType++) {
				capacity += container.get(bucket).get(ballType);
				ballTotal += container.get(ballType).get(bucket);
			}
			capacities.add(capacity);
			ballTotals.add(ballTotal);
		}

		System.out.println(capacities);
		System.out.println(ballTotals);

		Collections.sort(ballTotals);
		Collections.sort(capacities);

		if (ballTotals.equals(capacities)) {
			return "Possible";
		} else {
			return "Impossible";
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<List<Integer>> container = new ArrayList<>();

				IntStream.range(0, n).forEach(i -> {
					try {
						container.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
								.collect(toList()));
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				});

				String result = Result.organizingContainers(container);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}