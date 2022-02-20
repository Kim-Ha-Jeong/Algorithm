import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_1039 {
    static boolean[][] visited;
    static int MAX = 1000000;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        int k = Integer.parseInt(st.nextToken());
        visited = new boolean[k + 1][MAX + 1];

        bfs(str, k);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(String start, int k) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));

        int len = start.length();

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.depth == k) {
                ans = Math.max(Integer.parseInt(now.num), ans);
                continue;
            }

            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    int swapNum = swap(i, j, now.num);

                    if (swapNum != -1 && !visited[now.depth + 1][swapNum]) {
                        visited[now.depth + 1][swapNum] = true;
                        q.add(new Node(Integer.toString(swapNum), now.depth + 1));
                    }
                }
            }
        }
    }

    static int swap(int i, int j, String str) {
        char[] c = str.toCharArray();

        if (i == 0 && c[j] == '0') {
            return -1;
        }

        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;

        return Integer.parseInt(String.valueOf(c));
    }

    static class Node {
        String num;
        int depth;

        Node(String num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

}
