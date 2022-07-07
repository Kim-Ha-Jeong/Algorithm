import java.util.*;
import java.io.*;

public class BOJ_13913 {
    static int n, k;
    static boolean[] visited;
    static int[] parent;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        parent = new int[100001];

        if (n == k) {
            sb.append(0).append("\n");
            sb.append(n);
        } else if (n > k) {
            sb.append(n - k).append("\n");
            for (int i = n; i >= k; i--) {
                sb.append(i).append(" ");
            }
        } else {
            bfs();
            sb.append(ans).append("\n");

            Stack<Integer> stack = new Stack<>();
            stack.push(k);

            int location = k;
            while (location != n) {
                stack.push(parent[location]);
                location = parent[location];
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
        }

        bw.write(sb.toString());
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
            if (now == k) {
                ans = node.cnt;
                return;
            }

            int[] arr = { now - 1, now + 1, now * 2 };

            for (int i = 0; i < 3; i++) {
                if (arr[i] < 0 || arr[i] > 100000 || visited[arr[i]])
                    continue;
                visited[arr[i]] = true;
                parent[arr[i]] = now;
                q.add(new Node(arr[i], node.cnt + 1));
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
