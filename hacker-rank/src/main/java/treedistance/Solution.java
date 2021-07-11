package treedistance;

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


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {


            try {
                String k = bufferedReader.readLine();
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        List<Long> answer = findAnswer(edges, queries, n);


        for (Long i : answer) {

            bufferedWriter.write(String.valueOf(i));
            bufferedWriter.newLine();

        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static List<Long> findAnswer(List<List<Integer>> edges, List<List<Integer>> queries, int n) {

        long[][] dist = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = n+1;
            }
            dist[i][i] = 0;
        }

        for (List<Integer> edge : edges) {

            int u = edge.get(0);
            int v = edge.get(1);

            dist[u][v] = 1;
            dist[v][u] = 1;

            manipulate(dist, u);



        }


        List<Long> retVal = new ArrayList<>();
        for (List<Integer> query : queries) {


            long ans = 0;
            Integer[] t = new Integer[1];
            Integer[] data = query.toArray(t);

            int size = data.length;

            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    long distance = dist[data[i]][data[j]];
                    long addition = data[i] * data[j] * distance;
                    ans += addition;
                }
            }
            retVal.add(ans);

        }

        return retVal;
    }

    private static void manipulate(long[][] dist, int pivot, int support, int n) {

        for ( int i = 1; i <= n ; i++  ) {
            if ( dist[pivot][i] + dist[pivot][support] < dist[i][support]) {
                dist[i][support] = dist[i][pivot] + dist[pivot][support] ;
                dist[support][i] = dist[i][support];
             }
        }
    }


}
