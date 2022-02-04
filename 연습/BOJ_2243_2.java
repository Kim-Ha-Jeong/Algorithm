import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2243_2 {
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = 1;
        int MAX = 1000000;

        while (s < MAX) {
            s *= 2;
        }

        tree = new long[s * 2];

        int a, b;
        long c;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a == 2) {
                c = Long.parseLong(st.nextToken());
                update(1, s, 1, b, c);
            } else {
                int out = query(1, s, 1, b);
                update(1, s, 1, out, -1);
                sb.append(out).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void update(int left, int right, int node, int target, long diff) {
        if (target < left || right < target)
            return;

        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

    static int query(int left, int right, int node, long query) {
        if (left == right)
            return left;

        long leftVal = tree[node * 2];
        int mid = (left + right) / 2;
        if (leftVal < query) {
            return query(mid + 1, right, node * 2 + 1, query - leftVal);
        } else {
            return query(left, mid, node * 2, query);
        }
    }

}
