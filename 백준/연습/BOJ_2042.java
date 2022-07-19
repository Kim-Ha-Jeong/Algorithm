import java.util.*;
import java.io.*;

public class BOJ_2042 {
    static int n, m, k, s = 1;
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        getHeight();
        tree = new long[2 * s + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, 1, n);

        for (int i = 0; i < m + k; i++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, 1, n, b, diff);
            } else {
                sb.append(sum(1, 1, n, b, (int) c)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void update(int node, int start, int end, int idx, long diff) {
        if (idx > end || idx < start)
            return;

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    static long sum(int node, int start, int end, int left, int right) {
        if (right < start || end < left)
            return 0;

        if (end <= right && left <= start)
            return tree[node];

        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    static long init(int node, int start, int end) {
        if (start == end)
            return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    static void getHeight() {
        while (s < n) {
            s *= 2;
        }
    }

}
