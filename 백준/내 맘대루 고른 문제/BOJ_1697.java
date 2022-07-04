import java.util.*;
import java.io.*;

public class BOJ_1697 {
    static int n, k;
    static boolean[] visited;
    static int ans = -1;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];

        bfs();

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.num == k) {
                ans = now.second;
                break;
            }

            int[] tmp = { now.num - 1, now.num + 1, now.num * 2 };

            for (int i = 0; i < 3; i++) {
                if (tmp[i] < 0 || tmp[i] > 100000)
                    continue;
                if (visited[tmp[i]])
                    continue;
                q.add(new Node(tmp[i], now.second + 1));
                visited[tmp[i]] = true;
            }
        }
    }

    static class Node {
        int num;
        int second;

        Node(int num, int second) {
            this.num = num;
            this.second = second;
        }
    }

}
