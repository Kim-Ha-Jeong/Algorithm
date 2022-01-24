import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_1039 {
    static int MAX = 1000000;
    static boolean[][] visited;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n = st.nextToken();
        int k = Integer.parseInt(st.nextToken());

        visited = new boolean[k + 1][MAX + 1];

        bfs(n, k);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs(String start, int k) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, start));
        int len = start.length();

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.depth == k) {
                ans = Math.max(ans, Integer.parseInt(now.num));
                continue;
            }

            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    int next = Integer.parseInt(swap(now.num, i, j));

                    if (next != -1 && !visited[now.depth + 1][next]) {
                        visited[now.depth + 1][next] = true;
                        q.add(new Node(now.depth + 1, Integer.toString(next)));
                    }
                }
            }
        }
    }

    static String swap(String num, int i, int j) {
        char[] c = num.toCharArray();

        if (i == 0 && c[j] == '0')
            return "-1";

        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;

        return String.valueOf(c);
    }

    static class Node {
        int depth;
        String num;

        Node(int depth, String num) {
            this.depth = depth;
            this.num = num;
        }
    }

}
