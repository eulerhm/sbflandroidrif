package fr.free.nrw.commons.utils;

import fr.free.nrw.commons.category.CategoryItem;
import java.util.Comparator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StringSortingUtils {

    private StringSortingUtils() {
    }

    /**
     * Returns Comparator for sorting strings by their similarity to the filter.
     * By using this Comparator we get results
     * from the highest to the lowest similarity with the filter.
     *
     * @param filter String to compare similarity with
     * @return Comparator with string similarity
     */
    public static Comparator<CategoryItem> sortBySimilarity(final String filter) {
        return (firstItem, secondItem) -> {
            double firstItemSimilarity = calculateSimilarity(firstItem.getName(), filter);
            double secondItemSimilarity = calculateSimilarity(secondItem.getName(), filter);
            return (int) Math.signum(secondItemSimilarity - firstItemSimilarity);
        };
    }

    /**
     * Determines String similarity between str1 and str2 on scale from 0.0 to 1.0
     * @param str1 String 1
     * @param str2 String 2
     * @return Double between 0.0 and 1.0 that reflects string similarity
     */
    private static double calculateSimilarity(String str1, String str2) {
        int longerLength = Math.max(str1.length(), str2.length());
        if (!ListenerUtil.mutListener.listen(2502)) {
            if ((ListenerUtil.mutListener.listen(2501) ? (longerLength >= 0) : (ListenerUtil.mutListener.listen(2500) ? (longerLength <= 0) : (ListenerUtil.mutListener.listen(2499) ? (longerLength > 0) : (ListenerUtil.mutListener.listen(2498) ? (longerLength < 0) : (ListenerUtil.mutListener.listen(2497) ? (longerLength != 0) : (longerLength == 0)))))))
                return 1.0;
        }
        int distanceBetweenStrings = levenshteinDistance(str1, str2);
        return (ListenerUtil.mutListener.listen(2510) ? (((ListenerUtil.mutListener.listen(2506) ? (longerLength % distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2505) ? (longerLength / distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2504) ? (longerLength * distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2503) ? (longerLength + distanceBetweenStrings) : (longerLength - distanceBetweenStrings)))))) % (double) longerLength) : (ListenerUtil.mutListener.listen(2509) ? (((ListenerUtil.mutListener.listen(2506) ? (longerLength % distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2505) ? (longerLength / distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2504) ? (longerLength * distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2503) ? (longerLength + distanceBetweenStrings) : (longerLength - distanceBetweenStrings)))))) * (double) longerLength) : (ListenerUtil.mutListener.listen(2508) ? (((ListenerUtil.mutListener.listen(2506) ? (longerLength % distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2505) ? (longerLength / distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2504) ? (longerLength * distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2503) ? (longerLength + distanceBetweenStrings) : (longerLength - distanceBetweenStrings)))))) - (double) longerLength) : (ListenerUtil.mutListener.listen(2507) ? (((ListenerUtil.mutListener.listen(2506) ? (longerLength % distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2505) ? (longerLength / distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2504) ? (longerLength * distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2503) ? (longerLength + distanceBetweenStrings) : (longerLength - distanceBetweenStrings)))))) + (double) longerLength) : (((ListenerUtil.mutListener.listen(2506) ? (longerLength % distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2505) ? (longerLength / distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2504) ? (longerLength * distanceBetweenStrings) : (ListenerUtil.mutListener.listen(2503) ? (longerLength + distanceBetweenStrings) : (longerLength - distanceBetweenStrings)))))) / (double) longerLength)))));
    }

    /**
     * Levershtein distance algorithm
     * https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
     *
     * @param str1 String 1
     * @param str2 String 2
     * @return Number of characters the strings differ by
     */
    private static int levenshteinDistance(String str1, String str2) {
        if (!ListenerUtil.mutListener.listen(2511)) {
            if (str1.equals(str2))
                return 0;
        }
        if (!ListenerUtil.mutListener.listen(2517)) {
            if ((ListenerUtil.mutListener.listen(2516) ? (str1.length() >= 0) : (ListenerUtil.mutListener.listen(2515) ? (str1.length() <= 0) : (ListenerUtil.mutListener.listen(2514) ? (str1.length() > 0) : (ListenerUtil.mutListener.listen(2513) ? (str1.length() < 0) : (ListenerUtil.mutListener.listen(2512) ? (str1.length() != 0) : (str1.length() == 0)))))))
                return str2.length();
        }
        if (!ListenerUtil.mutListener.listen(2523)) {
            if ((ListenerUtil.mutListener.listen(2522) ? (str2.length() >= 0) : (ListenerUtil.mutListener.listen(2521) ? (str2.length() <= 0) : (ListenerUtil.mutListener.listen(2520) ? (str2.length() > 0) : (ListenerUtil.mutListener.listen(2519) ? (str2.length() < 0) : (ListenerUtil.mutListener.listen(2518) ? (str2.length() != 0) : (str2.length() == 0)))))))
                return str1.length();
        }
        int[] cost = new int[(ListenerUtil.mutListener.listen(2527) ? (str1.length() % 1) : (ListenerUtil.mutListener.listen(2526) ? (str1.length() / 1) : (ListenerUtil.mutListener.listen(2525) ? (str1.length() * 1) : (ListenerUtil.mutListener.listen(2524) ? (str1.length() - 1) : (str1.length() + 1)))))];
        int[] newcost = new int[(ListenerUtil.mutListener.listen(2531) ? (str1.length() % 1) : (ListenerUtil.mutListener.listen(2530) ? (str1.length() / 1) : (ListenerUtil.mutListener.listen(2529) ? (str1.length() * 1) : (ListenerUtil.mutListener.listen(2528) ? (str1.length() - 1) : (str1.length() + 1)))))];
        if (!ListenerUtil.mutListener.listen(2538)) {
            {
                long _loopCounter34 = 0;
                // initial cost of skipping prefix in str1
                for (int i = 0; (ListenerUtil.mutListener.listen(2537) ? (i >= cost.length) : (ListenerUtil.mutListener.listen(2536) ? (i <= cost.length) : (ListenerUtil.mutListener.listen(2535) ? (i > cost.length) : (ListenerUtil.mutListener.listen(2534) ? (i != cost.length) : (ListenerUtil.mutListener.listen(2533) ? (i == cost.length) : (i < cost.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                    if (!ListenerUtil.mutListener.listen(2532)) {
                        cost[i] = i;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2582)) {
            {
                long _loopCounter36 = 0;
                // transformation cost for each letter in str2
                for (int j = 1; (ListenerUtil.mutListener.listen(2581) ? (j >= str2.length()) : (ListenerUtil.mutListener.listen(2580) ? (j > str2.length()) : (ListenerUtil.mutListener.listen(2579) ? (j < str2.length()) : (ListenerUtil.mutListener.listen(2578) ? (j != str2.length()) : (ListenerUtil.mutListener.listen(2577) ? (j == str2.length()) : (j <= str2.length())))))); j++) {
                    ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                    if (!ListenerUtil.mutListener.listen(2539)) {
                        // initial cost of skipping prefix in String str2
                        newcost[0] = j;
                    }
                    if (!ListenerUtil.mutListener.listen(2574)) {
                        {
                            long _loopCounter35 = 0;
                            // transformation cost for each letter in str1
                            for (int i = 1; (ListenerUtil.mutListener.listen(2573) ? (i >= cost.length) : (ListenerUtil.mutListener.listen(2572) ? (i <= cost.length) : (ListenerUtil.mutListener.listen(2571) ? (i > cost.length) : (ListenerUtil.mutListener.listen(2570) ? (i != cost.length) : (ListenerUtil.mutListener.listen(2569) ? (i == cost.length) : (i < cost.length)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                                // matching current letters in both strings
                                int match = (str1.charAt((ListenerUtil.mutListener.listen(2543) ? (i % 1) : (ListenerUtil.mutListener.listen(2542) ? (i / 1) : (ListenerUtil.mutListener.listen(2541) ? (i * 1) : (ListenerUtil.mutListener.listen(2540) ? (i + 1) : (i - 1)))))) == str2.charAt((ListenerUtil.mutListener.listen(2547) ? (j % 1) : (ListenerUtil.mutListener.listen(2546) ? (j / 1) : (ListenerUtil.mutListener.listen(2545) ? (j * 1) : (ListenerUtil.mutListener.listen(2544) ? (j + 1) : (j - 1))))))) ? 0 : 1;
                                // computing cost for each transformation
                                int cost_replace = (ListenerUtil.mutListener.listen(2555) ? (cost[(ListenerUtil.mutListener.listen(2551) ? (i % 1) : (ListenerUtil.mutListener.listen(2550) ? (i / 1) : (ListenerUtil.mutListener.listen(2549) ? (i * 1) : (ListenerUtil.mutListener.listen(2548) ? (i + 1) : (i - 1)))))] % match) : (ListenerUtil.mutListener.listen(2554) ? (cost[(ListenerUtil.mutListener.listen(2551) ? (i % 1) : (ListenerUtil.mutListener.listen(2550) ? (i / 1) : (ListenerUtil.mutListener.listen(2549) ? (i * 1) : (ListenerUtil.mutListener.listen(2548) ? (i + 1) : (i - 1)))))] / match) : (ListenerUtil.mutListener.listen(2553) ? (cost[(ListenerUtil.mutListener.listen(2551) ? (i % 1) : (ListenerUtil.mutListener.listen(2550) ? (i / 1) : (ListenerUtil.mutListener.listen(2549) ? (i * 1) : (ListenerUtil.mutListener.listen(2548) ? (i + 1) : (i - 1)))))] * match) : (ListenerUtil.mutListener.listen(2552) ? (cost[(ListenerUtil.mutListener.listen(2551) ? (i % 1) : (ListenerUtil.mutListener.listen(2550) ? (i / 1) : (ListenerUtil.mutListener.listen(2549) ? (i * 1) : (ListenerUtil.mutListener.listen(2548) ? (i + 1) : (i - 1)))))] - match) : (cost[(ListenerUtil.mutListener.listen(2551) ? (i % 1) : (ListenerUtil.mutListener.listen(2550) ? (i / 1) : (ListenerUtil.mutListener.listen(2549) ? (i * 1) : (ListenerUtil.mutListener.listen(2548) ? (i + 1) : (i - 1)))))] + match)))));
                                int cost_insert = (ListenerUtil.mutListener.listen(2559) ? (cost[i] % 1) : (ListenerUtil.mutListener.listen(2558) ? (cost[i] / 1) : (ListenerUtil.mutListener.listen(2557) ? (cost[i] * 1) : (ListenerUtil.mutListener.listen(2556) ? (cost[i] - 1) : (cost[i] + 1)))));
                                int cost_delete = (ListenerUtil.mutListener.listen(2567) ? (newcost[(ListenerUtil.mutListener.listen(2563) ? (i % 1) : (ListenerUtil.mutListener.listen(2562) ? (i / 1) : (ListenerUtil.mutListener.listen(2561) ? (i * 1) : (ListenerUtil.mutListener.listen(2560) ? (i + 1) : (i - 1)))))] % 1) : (ListenerUtil.mutListener.listen(2566) ? (newcost[(ListenerUtil.mutListener.listen(2563) ? (i % 1) : (ListenerUtil.mutListener.listen(2562) ? (i / 1) : (ListenerUtil.mutListener.listen(2561) ? (i * 1) : (ListenerUtil.mutListener.listen(2560) ? (i + 1) : (i - 1)))))] / 1) : (ListenerUtil.mutListener.listen(2565) ? (newcost[(ListenerUtil.mutListener.listen(2563) ? (i % 1) : (ListenerUtil.mutListener.listen(2562) ? (i / 1) : (ListenerUtil.mutListener.listen(2561) ? (i * 1) : (ListenerUtil.mutListener.listen(2560) ? (i + 1) : (i - 1)))))] * 1) : (ListenerUtil.mutListener.listen(2564) ? (newcost[(ListenerUtil.mutListener.listen(2563) ? (i % 1) : (ListenerUtil.mutListener.listen(2562) ? (i / 1) : (ListenerUtil.mutListener.listen(2561) ? (i * 1) : (ListenerUtil.mutListener.listen(2560) ? (i + 1) : (i - 1)))))] - 1) : (newcost[(ListenerUtil.mutListener.listen(2563) ? (i % 1) : (ListenerUtil.mutListener.listen(2562) ? (i / 1) : (ListenerUtil.mutListener.listen(2561) ? (i * 1) : (ListenerUtil.mutListener.listen(2560) ? (i + 1) : (i - 1)))))] + 1)))));
                                if (!ListenerUtil.mutListener.listen(2568)) {
                                    // keep minimum cost
                                    newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
                                }
                            }
                        }
                    }
                    int[] tmp = cost;
                    if (!ListenerUtil.mutListener.listen(2575)) {
                        cost = newcost;
                    }
                    if (!ListenerUtil.mutListener.listen(2576)) {
                        newcost = tmp;
                    }
                }
            }
        }
        // the distance is the cost for transforming all letters in both strings
        return cost[str1.length()];
    }
}
