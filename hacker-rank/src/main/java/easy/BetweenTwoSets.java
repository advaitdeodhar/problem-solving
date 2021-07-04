package easy;

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

class BetweenTwoSets {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {

        int lcdA = getLCD(a);
        int lcdB = getLCD(b);

        if ( lcdA == lcdB && lcdA == 1) {
            return 0;
        }

        int scmA = getSCM(a);
        int temp = scmA;
        int ans = 0 ;
        while ( temp <= lcdB ) {
            if( lcdB % temp ==0 ) {
                ans++;
            }
            temp += scmA;
        }
        return ans;
    }

    private static int getSCM(List<Integer> a) {
        int ans = -1;

        for ( Integer i : a) {
            if ( ans == -1 ) {
                ans = i;
                continue;
            }
            ans = getSCM(ans, i);
        }
        return ans;
    }

    private static int getSCM(int p, int q) {

        int lcd = getLCD(p, q);

        return p*q/lcd;

    }

    private static int getLCD(List<Integer> a) {

        int ans = 0 ;

        for ( Integer i : a ) {
            if ( ans == 0 ) {
                ans = i;
                continue;
            }
            ans = getLCD(i, ans);

        }
        return ans;

    }

    private static int getLCD(int p, int q) {

        int large, small;
        if ( p > q ) {
            large = p;
            small = q;
        } else {
            large = q;
            small = p;
        }

        int rem = large % small;
        if ( rem == 0 ) {
            return small;
        }

        return getLCD( small, rem );

    }

}

class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
//
//        int n = Integer.parseInt(firstMultipleInput[0]);
//
//        int m = Integer.parseInt(firstMultipleInput[1]);
//
//        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        int total = BetweenTwoSets.getTotalX(arr, brr);
//
//        bufferedWriter.write(String.valueOf(total));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
        List<Integer> a = new ArrayList<>(Arrays.asList(100,99,98,97,96,95,94,93,92,91));
        List<Integer> b = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        int total = BetweenTwoSets.getTotalX(a,b);
        System.out.println(total);

    }
}
