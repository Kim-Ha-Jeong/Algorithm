import java.io.*;
import java.util.*;

public class BOJ_1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        int n, m;
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Queue<Node> q = new LinkedList<>();
            Integer[] arr = new Integer[n];

            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                q.add(new Node(i, x));
                arr[i] = x;
            }

            Arrays.sort(arr, Collections.reverseOrder());

            int idx = 0;
            int cnt = 0;
            while (!q.isEmpty() || idx < n) {
                Node now = q.peek();

                if (now.priority < arr[idx]) {
                    q.add(q.poll());
                } else {
                    q.poll();
                    cnt++;
                    idx++;
                    if (now.num == m)
                        break;
                }
            }

            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static class Node {
        int num;
        int priority;

        Node(int num, int priority) {
            this.num = num;
            this.priority = priority;
        }
    }

}
