import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2243 {
    static long[] tree;
    static int max = 1000000;
    static int s = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        while (s < max) {
            s *= 2;
        }
        tree = new long[s * 2];

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 2) {
                long c = Long.parseLong(st.nextToken());
                update(1, s, 1, b, c);
            } else if (a == 1) {
                int tmp = query(1, s, 1, b);
                sb.append(tmp).append("\n");
                update(1, s, 1, tmp, -1);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void update(int left, int right, int node, int target, long diff) {
        if (target < left || right < target)
            return;
        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static int query(int left, int right, int node, long query) {
        if (left == right) {
            return left;
        }

        long leftVal = tree[node * 2];
        int mid = (left + right) / 2;
        if (leftVal >= query) {
            return query(left, mid, node * 2, query);
        } else {
            return query(mid + 1, right, node * 2 + 1, query - leftVal);
        }

    }
}
