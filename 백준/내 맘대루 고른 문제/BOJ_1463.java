import java.io.*;
import java.util.*;

public class BOJ_1463 {
    static int n, ans = -1;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            int now = node.num;

            if (now == 1) {
                ans = node.cnt;
                return;
            }

            int[] arr = { now / 3, now / 2, now - 1 };

            for (int i = 0; i < 3; i++) {
                if ((now % 3 == 0 && i == 0) || (now % 2 == 0 && i == 1) || i == 2) {
                    if (arr[i] < 1 || arr[i] >= n + 1 || visited[arr[i]])
                        continue;
                    q.add(new Node(arr[i], node.cnt + 1));
                    visited[arr[i]] = true;
                }
            }
        }
    }

    static class Node {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
