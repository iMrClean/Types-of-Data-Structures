package ru.bmstu.lab1.common;

import java.util.HashMap;

public class DamerauLevenshtein {

    public final double distance(final String s1, final String s2) {

        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        int inf = s1.length() + s2.length();

        // Create and initialize the character array indices
        HashMap<Character, Integer> da = new HashMap<>();

        for (int d = 0; d < s1.length(); d++) {
            da.put(s1.charAt(d), 0);
        }

        for (int d = 0; d < s2.length(); d++) {
            da.put(s2.charAt(d), 0);
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] h = new int[s1.length() + 2][s2.length() + 2];

        // initialize the left and top edges of H
        for (int i = 0; i <= s1.length(); i++) {
            h[i + 1][0] = inf;
            h[i + 1][1] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            h[0][j + 1] = inf;
            h[1][j + 1] = j;
        }

        // fill in the distance matrix H
        // look at each character in s1
        for (int i = 1; i <= s1.length(); i++) {
            int db = 0;

            // look at each character in b
            for (int j = 1; j <= s2.length(); j++) {
                int i1 = da.get(s2.charAt(j - 1));
                int j1 = db;

                int cost = 1;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost = 0;
                    db = j;
                }

                h[i + 1][j + 1] = min(
                        h[i][j] + cost, // substitution
                        h[i + 1][j] + 1, // insertion
                        h[i][j + 1] + 1, // deletion
                        h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
            }

            da.put(s1.charAt(i - 1), i);
        }

        return h[s1.length() + 1][s2.length() + 1];
    }

    private static int min(final int a, final int b, final int c, final int d) {
        return Math.min(a, Math.min(b, Math.min(c, d)));
    }
}