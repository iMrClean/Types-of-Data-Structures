package ru.bmstu.lab1.common;

public class Levenshtein {

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

        if (s1.length() == 0) {
            return s2.length();
        }

        if (s2.length() == 0) {
            return s1.length();
        }

        int[] v0 = new int[s2.length() + 1];
        int[] v1 = new int[s2.length() + 1];

        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            System.arraycopy(v1, 0, v0, 0, v0.length);
            v1[0] = i + 1;

            for (int j = 0; j < s2.length(); j++) {
                int cost = (s1.charAt(i) != s2.charAt(j)) ? 1 : 0;
                v1[j + 1] = min(
                        v1[j] + 1,
                        v0[j + 1] + 1,
                        v0[j] + cost);   // Cost of substitution
            }
        }

        return v0[s2.length() - 1];
    }

    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }

}