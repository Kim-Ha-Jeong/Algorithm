import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ_7578 {
    static long[] tree;
    static int s = 1;
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        while (s < n) {
            s *= 2;
        }

        tree = new long[s * 2];
        int[] a = new int[n + 1];
        HashMap<Integer, Integer> b = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            b.put(tmp, i);
        }

        for (int i = 1; i < n + 1; i++) {
            int num = b.get(a[i]);
            if (i != 0)
                ans += query(1, s, 1, num + 1, s);
            update(1, s, 1, num, 1);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void update(int left, int right, int node, int target, int diff) {
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

    static long query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left)
            return 0;
        else if (leftQuery <= left && right <= rightQuery)
            return tree[node];
        else {
            int mid = (left + right) / 2;
            long leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftValue + rightValue;
        }
    }

}
