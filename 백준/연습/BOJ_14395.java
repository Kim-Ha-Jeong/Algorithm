import java.util.*;
import java.io.*;

public class BOJ_14395 {

    static int s, t, ans = -1;
    static boolean[] visited;
    static String str;
    static char[] op = { '*', '+', '/' };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        visited = new boolean[Math.max(s, t) + 1];

        if (s == t) {
            sb.append(0);
        } else if (t == 0) {
            sb.append("-");
        } else {
            bfs();

            if (ans == -1) {
                sb.append(ans);
            } else {
                sb.append(str);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        visited[s] = true;
        q.add(new Node(s, ""));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.num == t) {
                ans = 0;
                str = now.word;
                break;
            }

            long num = now.num;
            long last = num == 0 ? -1 : 1;
            long[] arr = { num * num, num + num, last };

            for (int i = 0; i < 3; i++) {
                long next = arr[i];
                if (next < 0 || next > t || visited[(int) next])
                    continue;
                visited[(int) next] = true;
                q.add(new Node(next, now.word + op[i]));
            }
        }
    }

    static class Node {
        long num;
        String word;

        Node(long num, String word) {
            this.num = num;
            this.word = word;
        }
    }

}
