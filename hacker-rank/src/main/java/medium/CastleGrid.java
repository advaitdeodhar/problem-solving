package medium;

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

class CastleGrid {

    /*
     * Complete the 'minimumMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING_ARRAY grid
     *  2. INTEGER startX
     *  3. INTEGER startY
     *  4. INTEGER goalX
     *  5. INTEGER goalY
     */

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {

        int rows = grid.size();
        int cols = grid.get(0).toCharArray().length;


        int[][] gridData = new int[rows][cols];
        int[][] ansData = new int[rows][cols];

        int i = 0;
        int j = 0;
        for (String rowdata : grid) {
            j = 0;
            for (Character c : rowdata.toCharArray()) {
                if (c == 'X') {
                    gridData[i][j] = 1;
                    ansData[i][j] = 100001;
                } else {
                    gridData[i][j] = 0;
                    ansData[i][j] = 100000;
                }
                j++;
            }
            i++;
        }

        ansData[startX][startY] = 0;

        Queue<Integer> process = new LinkedList<>();

        process.add(startX);
        process.add(startY);
        process.add(0);
        process.add(startX);
        process.add(startY);
        process.add(1);

        while (!process.isEmpty() && ansData[goalX][goalY] == 100000) {
            int fRow = process.poll();
            int fCol = process.poll();
            int mode = process.poll();

            int source = ansData[fRow][fCol];

            if (mode == 0) {
                int cRow = fRow;
                int cCol = fCol;
                while (--cRow >= 0 && gridData[cRow][cCol] != 1 ) {
                    if ( ansData[cRow][cCol] > ( source + 1 )) {
                        ansData[cRow][cCol] = source + 1;
                        process.add(cRow);
                        process.add(cCol);
                        process.add(1);
                    }
                }


                cRow = fRow;
                cCol = fCol;
                while (++cRow < rows && gridData[cRow][cCol] != 1) {
                    if ( ansData[cRow][cCol] > (source + 1)) {
                        ansData[cRow][cCol] =  source + 1;
                        process.add(cRow);
                        process.add(cCol);
                        process.add(1);
                    }
                }
            }


            if (mode == 1) {
                int cRow = fRow;
                int cCol = fCol;
                while (--cCol >= 0 && gridData[cRow][cCol] != 1) {
                    if ( ansData[cRow][cCol] > (source + 1)) {
                        ansData[cRow][cCol] = source + 1;
                        process.add(cRow);
                        process.add(cCol);
                        process.add(0);
                    }

                }

                cRow = fRow;
                cCol = fCol;
                while (++cCol < cols && gridData[cRow][cCol] != 1) {
                    if ( ansData[cRow][cCol] > (source + 1)) {
                        ansData[cRow][cCol] = source + 1;
                        process.add(cRow);
                        process.add(cCol);
                        process.add(0);
                    }
                }
            }
        }

        return ansData[goalX][goalY];

    }

}

class CastleGridSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int startX = Integer.parseInt(firstMultipleInput[0]);

        int startY = Integer.parseInt(firstMultipleInput[1]);

        int goalX = Integer.parseInt(firstMultipleInput[2]);

        int goalY = Integer.parseInt(firstMultipleInput[3]);

        int result = CastleGrid.minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
//        List<String> data = new ArrayList<>(Arrays.asList(".X.", ".X.", "..."));
//        System.out.println(CastleGrid.minimumMoves(data, 0,0,0,2));
    }
}
