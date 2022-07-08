import java.io.*;
import java.util.*;

public class BOJ_15662 {
    static int t, k;
    static int[][] gear;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        gear = new int[t][8];

        for (int i = 0; i < t; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(s[j]);
            }
        }

        k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            Queue<Node> q = new LinkedList<>();
            boolean[] visited = new boolean[t];
            q.add(new Node(a - 1, dir));

            while (!q.isEmpty()) {
                Node now = q.poll();

                int num = now.num;
                int nextD = now.dir * (-1);
                visited[num] = true;

                if (num + 1 < t && !visited[num + 1]) {
                    if (gear[num + 1][6] != gear[num][2]) {
                        q.add(new Node(num + 1, nextD));
                    }
                }

                if (num - 1 >= 0 && !visited[num - 1]) {
                    if (gear[num - 1][2] != gear[num][6]) {
                        q.add(new Node(num - 1, nextD));
                    }
                }

                rotate(now.dir, num);
            }
        }

        int ans = 0;
        for (int i = 0; i < t; i++) {
            if (gear[i][0] == 1)
                ans++;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void rotate(int d, int num) {
        if (d == -1) {
            int tmp = gear[num][0];
            for (int i = 0; i < 7; i++) {
                gear[num][i] = gear[num][i + 1];
            }
            gear[num][7] = tmp;
        } else {
            int tmp = gear[num][7];
            for (int i = 7; i >= 1; i--) {
                gear[num][i] = gear[num][i - 1];
            }
            gear[num][0] = tmp;
        }
    }

    static class Node {
        int num;
        int dir;

        Node(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

}
