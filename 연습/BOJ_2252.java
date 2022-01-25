import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class BOJ_2252 {
    static int n, m;
    static int[] front;
    static ArrayList<Integer>[] list;
    static Queue<Integer> q = new LinkedList<Integer>();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        front = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            front[b]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if (front[i] == 0) {
                q.add(i);
            }
        }

        solve(sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void solve(StringBuffer sb) {

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int next : list[now]) {
                front[next]--;
                if (front[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

}
