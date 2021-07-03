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

class RepeatedString {

    /*
     * Complete the 'repeatedString' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. LONG_INTEGER n
     */

    public static long repeatedString(String s, long n) {

        Map<String, Long> matchMap = new HashMap<>();



        int len = s.length();
        long[] counts = new long[len+1];
        int i = 0;
        int count = 0;
        counts[i] = 0;
        for ( char ch : s.toCharArray()) {
            if (ch == 'a') {
                count++;
            }
            counts[i] = count;

        }
        long complete = n/len;
        int incomplete = (int) (n%len);

        long answer = complete * count + counts[incomplete-1];

        return answer;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String s = bufferedReader.readLine();
//
//        long n = Long.parseLong(bufferedReader.readLine().trim());
//
//        long result = repeatedString(s, n);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
        System.out.println(repeatedString("aba", 10L));
    }
}
