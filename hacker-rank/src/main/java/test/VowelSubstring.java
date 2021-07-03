package test;

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



class VowelSubstring {

    public static String findSubstringOne(String s, int k) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        int count = 0;
        BitSet bs =new BitSet();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int i=0;
        for ( Character c : s.toCharArray() ) {
            if ( vowels.contains(c)) {
                bs.set(i);
                count++;
                tm.put(i, count);
            }
            i++;
        }

        count = tm.get(tm.higherKey(k-1)) - 1;

        TreeMap<Integer, Integer> cache = new TreeMap<>(Collections.reverseOrder());
        cache.putIfAbsent(count,0);

        int current = k-1;
        int next = bs.nextSetBit(current);
        while( next != -1 ) {

            int currentCount = tm.get(next);
            int start = next - k + 1;

            int currentstartCount = tm.get(tm.higherKey(start-1)) - 1;
            count = currentCount - currentstartCount;

            cache.putIfAbsent(count, start);
            current = next;
            next = bs.nextSetBit(current+1);
        }

        int max = cache.firstEntry().getKey();

        if ( max == 0 ) {
            return "Not found!";
        }
        return s.substring(cache.firstEntry().getValue(), cache.firstEntry().getValue() + k);

    }

    /*
     * Complete the 'findSubstring' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String findSubstring(String s, int k) {

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int count = 0;
        for ( int i = 0; i < k ; i++ ) {
            char c = s.charAt(i);
            if ( vowels.contains(c)) {
                count++;
            }
        }

        int start = 0;
        int end = k-1;
        TreeMap<Integer, Integer> cache = new TreeMap<>(Collections.reverseOrder());
        cache.putIfAbsent(count,0);

        for (int i = k; i < s.length(); i++) {
            char gain = s.toCharArray()[i];
            char loose = s.toCharArray()[i-k];

            boolean gained = vowels.contains(gain);
            boolean lost = vowels.contains(loose);

            if (gained) {
                count++;
            }

            if ( lost ) {
                count--;
            }

            cache.putIfAbsent(count, i-k+1);

        }

        int max = cache.firstEntry().getKey();

        if ( max == 0 ) {
            return "Not found!";
        }
        return s.substring(cache.firstEntry().getValue(), cache.firstEntry().getValue() + k);

    }

    public static void main(String[] args) throws IOException {
//
        System.out.println(findSubstringOne("azerdii", 5));
    }
}
