import java.util.*;
import java.io.*;

public class BOJ_20055 {
    static int n, k, level = 1, cnt = 0;
    static int[] map;
    static boolean[] robot;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2 * n + 1];
        robot = new boolean[2 * n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 2 * n + 1; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            rotate();
            move();
            add();
            if (cnt >= k)
                break;
            level++;
        }

        sb.append(level);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void rotate() {
        int tmp = map[2 * n];
        for (int i = 2 * n - 1; i >= 1; i--) {
            map[i + 1] = map[i];
        }
        map[1] = tmp;

        for (int i = n - 1; i >= 1; i--) {
            robot[i + 1] = robot[i];
        }
        robot[1] = robot[n] = false;
    }

    static void move() {
        for (int i = n - 1; i >= 1; i--) {
            if (!robot[i + 1] && robot[i] && map[i + 1] >= 1) {
                robot[i + 1] = true;
                robot[i] = false;
                map[i + 1]--;
                if (map[i + 1] == 0)
                    cnt++;
            }
        }
    }

    static void add() {
        if (map[1] > 0) {
            robot[1] = true;
            map[1]--;
            if (map[1] == 0)
                cnt++;
        }
    }

}
