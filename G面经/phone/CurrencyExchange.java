package google;

import java.util.*;

public class CurrencyExchange {
	/**
	Given a file containing ratios between labels and query file containing pair of labels, find the ratio between each label pair (write it to an output file)
	Input file example:
	Source	Ratio		Destination
	GBP		0.69		USD
	Yard	1.09		Meter
	EUR		0.0077		YEN
	YEN		167.75		GBP

	Query file example:
	Source	Destination
	EUR		USD
	Meter	Yard

	Output file example:
	Source	Ratio		Destination
	EUR		0.89		USD
	Meter	0.91		Yard

	Write a program that produces a list of ratio records using the provided ratio records and queries.
*/
	class Ratio {
		String num;
		String denom;
		double ratio;

		public Ratio(String num, String denom, double ratio) {
			this.num = num;
			this.denom = denom;
			this.ratio = ratio;
		}
	}

	class Pair {
		String first;
		String second;

		public Pair(String first, String second) {
			this.first = first;
			this.second = second;
		}
	}

	double[] queryRatios(List<Ratio> ratios, List<Pair> queries) {
		Map<String, Map<String, Double>> adjList = new HashMap<>();
		for (Ratio ratio : ratios) {
      /*
      computeIfAbsent java 8 的fancy写法。。。。
      computeIfAbsent
      default V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)
      If the specified key is not already associated with a value (or is mapped to null),
      attempts to compute its value using the given mapping function and enters it into this map unless null.
      */
			adjList.computeIfAbsent(ratio.num, k -> new HashMap<>()).put(ratio.denom, ratio.ratio);
			adjList.computeIfAbsent(ratio.denom, k -> new HashMap<>()).put(ratio.num, 1.0 / ratio.ratio);
		}

		double[] result = new double[queries.size()];
		Map<String, Double> memorization = new HashMap<>();
		for (int i = 0; i < result.length; i++) {
			Pair query = queries.get(i);
			result[i] = processQuery(query.first, query.second, adjList, memorization);
		}

		return result;
	}

	private static final double EPSILON = 0.001;

	// Map<String, Double> memorization, Set<String> visitedSet
	// -1.0 not possible
	// -2.0 as visited
	private double processQuery(String num, String denom, Map<String, Map<String, Double>> adjList,
			Map<String, Double> memorization) {
		String key = num + ":" + denom;
		Double res = memorization.get(key);

		if (res != null) {
			if (Math.abs(res + 2.0) < EPSILON) {
				return -1.0;
			}
			return res;
		}

		if (!adjList.containsKey(num) || !adjList.containsKey(denom)) {
			return -1.0;
		}

		if (num.equals(denom)) {
			return 1.0;
		}

		memorization.put(key, -2.0);
		for (Map.Entry<String, Double> entry : adjList.get(num).entrySet()) {
			double ans = processQuery(entry.getKey(), denom, adjList, memorization);
			if (ans > 0) {
				memorization.put(key, ans);
				return entry.getValue() * ans;
			}
		}

		memorization.put(key, -1.0);
		return -1.0;
	}

	public static void main(String[] args) {
		CurrencyExchange solution = new CurrencyExchange();
		List<Ratio> ratios = new ArrayList<>() {{
			add(solution.new Ratio("USD", "GBP", 0.69));
			add(solution.new Ratio("YEN", "EUR", 0.0077));
			add(solution.new Ratio("GBP", "YEN", 167.75));
			add(solution.new Ratio("USD", "RP", 212.02));
			add(solution.new Ratio("Yard", "Meter", 1.09));
		}};
		List<Pair> queries = new ArrayList<>() {{
			add(solution.new Pair("USD", "EUR"));
			add(solution.new Pair("YEN", "YEN"));
			add(solution.new Pair("Meter", "Yard"));
			add(solution.new Pair("Meter", "RP")); //should return -1.0
			add(solution.new Pair("Yard", "RP"));  //should return -1.0
		}};

		double[] res = solution.queryRatios(ratios, queries);
		for (double ratio: res) {
			System.out.println(ratio);
		}
	}
}
