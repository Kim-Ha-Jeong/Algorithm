import java.util.*;
import java.io.*;

class BOJ_16939 {
    static int[] cube = new int[25];
    static int[][][] possibility = { { { 5, 2, 7, 4 }, { 9, 6, 11, 8 }, { 24, 10, 22, 12 }, { 1, 3, 23, 21 } },
            { { 24, 2, 22, 4 }, { 1, 6, 3, 8 }, { 5, 10, 7, 12 }, { 9, 23, 11, 21 } },
            { { 5, 6, 15, 16 }, { 17, 18, 7, 8 }, { 21, 22, 19, 20 }, { 13, 14, 23, 24 } },
            { { 13, 14, 7, 8 }, { 5, 6, 19, 20 }, { 17, 18, 23, 24 }, { 21, 22, 15, 16 } },
            { { 17, 19, 1, 2 }, { 10, 18, 9, 20 }, { 16, 12, 14, 11 }, { 3, 15, 4, 13 } },
            { { 3, 4, 18, 20 }, { 17, 12, 19, 11 }, { 10, 15, 9, 13 }, { 16, 1, 14, 2 } } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < 25; i++) {
            cube[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        if (isSame(13, 16) && isSame(17, 20)) {
            flag = solve(0) || solve(1);
        }

        if (!flag && isSame(1, 4) && isSame(9, 12)) {
            flag = solve(2) || solve(3);
        }

        if (!flag && isSame(5, 8) && isSame(21, 24)) {
            flag = solve(4) || solve(5);
        }

        int ans = flag ? 1 : 0;
        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static boolean solve(int start) {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (!flag)
                break;
            int num = cube[possibility[start][i][0]];
            for (int j = 1; j < 4; j++) {
                if (num != cube[possibility[start][i][j]]) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    static boolean isSame(int start, int end) {
        int num = cube[start];
        for (int i = start; i < end + 1; i++) {
            if (cube[i] != num)
                return false;
        }

        return true;
    }
}