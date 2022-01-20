import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static long[] tree;
    static int s = 1, n;
    static long[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int add = m + k;
        num = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        while (s < n) {
            s *= 2;
        }

        tree = new long[s * 2];
        init();

        while (add-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, s, 1, b, c - tree[s + b - 1]);
            } else if (a == 2) {
                sb.append(query(1, s, 1, b, (int) c)).append("\n");
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

    static long query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (rightQuery < left || right < leftQuery)
            return 0;
        else if (leftQuery <= left && right <= rightQuery)
            return tree[node];
        else {
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightResult = query(mid + 1, right, node * 2 + 1, leftQuery,
                    rightQuery);
            return leftResult + rightResult;
        }
    }

    static void init() {
        for (int i = 1; i < n + 1; i++) {
            tree[s + i - 1] = num[i];
        }

        for (int i = s - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    // static void init() {
    // for (int i = 0; i < n; i++) {
    // tree[s + i] = num[i];
    // }

    // for (int i = s - 1; i > 0; i--) {
    // tree[i] = tree[i * 2] + tree[i * 2 + 1];
    // }
    // }

    // static long query(int left, int right, int node, int queryLeft, int
    // queryRight) {
    // if (queryRight < left || right < queryLeft)
    // return 0;
    // else if (queryLeft <= left && right <= queryRight) {
    // return tree[node];
    // } else {
    // int mid = (left + right) / 2;
    // long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
    // long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft,
    // queryRight);
    // return leftResult + rightResult;
    // }
    // }

    // static void update(int left, int right, int node, int target, int diff) {
    // if (target < left || right < target)
    // return;
    // else {
    // tree[node] += diff;
    // if (left != right) {
    // int mid = (left + right) / 2;
    // update(left, mid, node * 2, target, diff);
    // update(mid + 1, right, node * 2 + 1, target, diff);
    // }
    // }
    // }

    // static long queryBU(int queryLeft, int queryRight) {
    // int left = s + queryLeft - 1;
    // int right = s + queryRight - 1;

    // long sum = 0;

    // while (left <= right) {
    // if (left % 2 == 1)
    // sum += tree[left++];
    // if (right % 2 == 0)
    // sum += tree[right--];

    // left /= 2;
    // right /= 2;
    // }

    // return sum;
    // }

    // static void updateBU(int target, long value) {
    // int node = s + target - 1;
    // tree[node] = value;

    // node /= 2;
    // while (node > 0) {
    // tree[node] = tree[node * 2] + tree[node * 2 + 1];
    // node /= 2;
    // }
    // }

}
