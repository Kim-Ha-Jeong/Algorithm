import java.util.*;
import java.io.*;

public class BOJ_1963 {
    static int tc, start, end, ans = -1;
    static int MAX = 10000;
    static boolean[] visited = new boolean[MAX];
    static boolean[] prime = new boolean[MAX];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());
        isPrime();

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            visited = new boolean[MAX];

            bfs();

            if (ans == -1)
                sb.append("Impossible");
            else
                sb.append(ans);

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.num == end) {
                ans = now.cnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                char[] c = String.valueOf(now.num).toCharArray();
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    c[i] = Integer.toString(j).charAt(0);

                    int next = Integer.parseInt(new String(c));

                    if (visited[next] || !prime[next])
                        continue;

                    visited[next] = true;
                    q.add(new Node(next, now.cnt + 1));
                }
            }
        }
    }

    static void isPrime() {
        Arrays.fill(prime, true);
        for (int i = 2; i * i < MAX; i++) {
            for (int j = i * i; j < MAX; j += i) {
                prime[j] = false;
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
