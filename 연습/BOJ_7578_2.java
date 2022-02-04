import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ_7578_2 {
    static long[] tree;
    static int s = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        int[] B = new int[n];

        while (s < n) {
            s *= 2;
        }

        tree = new long[s * 2];

        HashMap<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            map.put(A[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            B[i] = map.get(tmp);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            int num = B[i];
            if (i != 0) {
                sum += query(1, s, num + 1, s, 1);
            }
            update(1, s, 1, num, 1);
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void update(int left, int right, int node, int target, int diff) {
        if (target < left || right < target)
            return;

        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

    static long query(int left, int right, int leftQ, int rightQ, int node) {
        if (leftQ <= left && right <= rightQ) {
            return tree[node];
        } else if (right < leftQ || rightQ < left) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            long leftVal = query(left, mid, leftQ, rightQ, node * 2);
            long rightVal = query(mid + 1, right, leftQ, rightQ, node * 2 + 1);
            return leftVal + rightVal;
        }
    }

}
