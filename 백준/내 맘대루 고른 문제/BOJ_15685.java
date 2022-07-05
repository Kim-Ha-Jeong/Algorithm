import java.util.*;
import java.io.*;

public class BOJ_15685 {
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list = new ArrayList<>();

            list.add(d);
            for (int i = 0; i < g; i++) {
                int size = list.size();
                for (int j = size - 1; j >= 0; j--) {
                    int a = list.get(j);
                    list.add((a + 1) % 4);
                }
            }

            map[y][x] = true;

            for (int dir : list) {
                y += dy[dir];
                x += dx[dir];
                map[y][x] = true;
            }
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j + 1] && map[i + 1][j] && map[i][j + 1])
                    cnt++;
            }
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();

    }

}
