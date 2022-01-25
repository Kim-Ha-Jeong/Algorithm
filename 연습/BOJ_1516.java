import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_1516 {
    static int n;
    static int[] time;
    static int[] result;
    static int[] check;
    static ArrayList<Integer> list[];
    static Queue<Integer> q = new LinkedList<>();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        time = new int[n + 1];
        list = new ArrayList[n + 1];
        check = new int[n + 1];
        result = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            time[i] = a;

            if (b != -1) {
                while (b != -1) {
                    list[b].add(i);
                    check[i]++;
                    b = Integer.parseInt(st.nextToken());
                }
            } else {
                result[i] = a;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (check[i] == 0)
                q.add(i);
        }

        topologic();

        for (int i = 1; i < n + 1; i++) {
            sb.append(result[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void topologic() {
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                check[next]--;
                result[next] = Math.max(result[next], result[now] + time[next]);

                if (check[next] == 0)
                    q.add(next);
            }

        }
    }

}
