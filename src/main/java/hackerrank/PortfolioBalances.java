package hackerrank;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PortfolioBalances {

    public static long maxValue(int portfolioCount, int[][] contributions) {
        if (portfolioCount < 1 || contributions == null) {
            return 0;
        }

        int N = contributions[0].length;

        if (N != 3) {
            return 0;
        }

        long[] portfolios = new long[portfolioCount];
        long maxValue = 0;
        for (int i = 0; i < contributions.length; i++) {
            int left = contributions[i][0] - 1;
            int right = contributions[i][1] - 1;
            int contribution = contributions[i][2];
            if (isValidIndex(left, 0, portfolioCount - 1) && isValidIndex(right, 0, portfolioCount - 1)) {
                for (int j = left; j <= right; j++) {
                    portfolios[j] += contribution;
                    if (portfolios[j] > maxValue) {
                        maxValue = portfolios[j];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(portfolios));
        return maxValue;
    }

    public static long maxValue(int n, List<List<Integer>> rounds) {
        if (n < 1 || rounds == null || rounds.size() == 0) {
            return 0;
        }
        long[] portfolios = new long[n];
        long maxValue = 0;
        for (List<Integer> round : rounds) {
            if (round == null || round.size() != 3) {
                continue;
            }
            int left = round.get(0) - 1;
            int right = round.get(1) - 1;
            int contribution = round.get(2);
            if (!isValidIndex(left, 0, n - 1) || !isValidIndex(right, 0, n - 1)) {
                continue;
            }
            for (int j = left; j <= right; j++) {
                portfolios[j] += contribution;
                if (portfolios[j] > maxValue) {
                    maxValue = portfolios[j];
                }
            }
        }
        return maxValue;
    }

    public static long maxValue1(int n, List<List<Integer>> rounds) {
        if (n < 1 || rounds == null || rounds.size() == 0) {
            return 0;
        }
        Map<String, Long> mergedRounds = new ConcurrentHashMap<>();
        long maxValue = 0;
        for (int j = 0; j < rounds.size(); j++) {
            List<Integer> round = rounds.get(j);
            if (round == null || round.size() != 3) {
                continue;
            }
            int left = round.get(0) - 1;
            int right = round.get(1) - 1;
            int contribution = round.get(2);
            if (!isValidIndex(left, 0, n - 1) || !isValidIndex(right, 0, n - 1)) {
                continue;
            }
            for (int i = left; i <= right; i++) {
                long value = mergedRounds.compute(String.valueOf(i), (k, v) -> {

                    if (v == null) {
                        return Long.valueOf(contribution);
                    }
                    return v + contribution;
                });
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }

    public static long maxValueMultithreaded(int n, List<List<Integer>> rounds) {
        if (n < 1 || rounds == null || rounds.size() == 0) {
            return 0;
        }
        Map<String, Long> mergedRounds = new ConcurrentHashMap<>();
        Map<String, Long> result = new ConcurrentHashMap<>();
        result.put("res", 0l);
        Consumer<List<Integer>> listConsumer = round -> {
            if (round == null || round.size() != 3) {
                return;
            }
            int left = round.get(0) - 1;
            int right = round.get(1) - 1;
            int contribution = round.get(2);
            if (!isValidIndex(left, 0, n - 1) || !isValidIndex(right, 0, n - 1)) {
                return;
            }
            for (int i = left; i <= right; i++) {
                long value = mergedRounds.compute(String.valueOf(i), (k, v) -> {

                    if (v == null) {
                        return Long.valueOf(contribution);
                    }
                    return v + contribution;
                });
                if (value > result.get("res")) {
                    result.put("res", value);
                }
            }
        };
        rounds.parallelStream().forEach(listConsumer);
        return result.get("res");
    }

    public static long maxValueMultithreaded1(int n, List<List<Integer>> rounds) {
        if (n < 1 || rounds == null || rounds.size() == 0) {
            return 0;
        }
        Map<String, Long> mergedRounds = new ConcurrentHashMap<>();
        Map<String, Long> result = new ConcurrentHashMap<>();
        result.put("res", 0l);
        ExecutorService service = Executors.newFixedThreadPool(4);
        Consumer<List<Integer>> maxValueComputer = round -> {
            if (round == null || round.size() != 3) {
                return;
            }
            int left = round.get(0) - 1;
            int right = round.get(1) - 1;
            int contribution = round.get(2);
            if (!isValidIndex(left, 0, n - 1) || !isValidIndex(right, 0, n - 1)) {
                return;
            }
            for (int i = left; i <= right; i++) {
                long value = mergedRounds.compute(String.valueOf(i), (k, v) -> {

                    if (v == null) {
                        return Long.valueOf(contribution);
                    }
                    return v + contribution;
                });
                if (value > result.get("res")) {
                    result.put("res", value);
                }
            }
        };

        List<Future<?>> futures = IntStream.range(0, rounds.size())
                .filter(i -> i % 100 == 0)
                .mapToObj(i -> rounds.subList(i, Math.min(i + n, rounds.size())))
                .flatMap(List::stream)
                .map(round ->
                        service.submit(() -> maxValueComputer.accept(round)))
                .collect(Collectors.toList());

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        service.shutdownNow();
        return result.get("res");
    }

    public static long maxValueOptimised(int n, List<List<Integer>> rounds) {
        if (n < 1 || rounds == null || rounds.size() == 0) {
            return 0;
        }
        Map<Long, Map<Integer, Long>> portfoliosByContribution = new HashMap<>();
        final long[] maxValue = {0};
        for (int j = 0; j < rounds.size(); j++) {
            List<Integer> round = rounds.get(j);
            if (round == null || round.size() != 3) {
                continue;
            }
            int left = round.get(0) - 1;
            int right = round.get(1) - 1;
            long contribution = round.get(2);
            if (!isValidIndex(left, 0, n - 1) || !isValidIndex(right, 0, n - 1)) {
                continue;
            }
            portfoliosByContribution.compute(contribution, (c, m) -> {
                if (m == null) {
                    m = new HashMap<>();
                }
                for (int i = left; i <= right; i++) {
                    m.compute(i, (k, v) -> {
                        if (v == null) {
                            return Long.valueOf(contribution);
                        }
                        return v + contribution;
                    });
                }
                return m;
            });
        }

        Map<Integer, Long> aggregatedContributionsByPortfolio = new HashMap<>();
        portfoliosByContribution.values()
                .forEach(pf -> pf.forEach((k, v) -> {
                    Long sum = aggregatedContributionsByPortfolio.merge(k, v, (v1, v2) -> v1 + v2);
                    if (sum > maxValue[0]) {
                        maxValue[0] = sum;
                    }
                }));
        return maxValue[0];
    }

    private static boolean isValidIndex(int index, int start, int end) {
        return index >= start && index <= end;
    }

    public static void main(String[] args) {
        int[][] contributions = new int[][]{{1, 2, 100}, {2, 5, 100}, {3, 4, 100}};
        System.out.println("max value = " + maxValue(5, contributions));

        contributions = new int[][]{{2, 3, 603}, {1, 1, 286}, {4, 4, 882}};
        System.out.println("max value = " + maxValue(4, contributions));

        List<List<Integer>> rounds = new ArrayList<>();
        rounds.add(Arrays.asList(1, 2, 100));
        rounds.add(Arrays.asList(2, 5, 100));
        rounds.add(Arrays.asList(3, 4, 100));
        System.out.println("max value with list = " + maxValue(5, rounds));
        System.out.println("max value opt = " + maxValueOptimised(5, rounds));

        rounds = new ArrayList<>();
        rounds.add(Arrays.asList(2, 3, 603));
        rounds.add(Arrays.asList(1, 1, 286));
        rounds.add(Arrays.asList(4, 4, 882));
        System.out.println("max value with list = " + maxValue(4, rounds));
        System.out.println("max value opt = " + maxValueOptimised(4, rounds));
    }
}
