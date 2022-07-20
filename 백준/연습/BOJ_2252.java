import java.util.*;
import java.io.*;

public class BOJ_2252 {
    static int n, m;
    static ArrayList<Integer>[] list;
    static int[] arrow;
    static StringBuilder sb = new StringBuilder();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        arrow = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            arrow[b]++;
        }

        tropicalSort();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void tropicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            if (arrow[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int next : list[now]) {
                if (arrow[next] == 0)
                    continue;
                arrow[next]--;
                if (arrow[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

}
