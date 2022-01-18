import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2805 {
    static int[] tree;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new int[n];

        st = new StringTokenizer(br.readLine());
        int high = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            if (high < tree[i])
                high = Math.max(high, tree[i]);
        }

        int low = 0;
        int mid;

        while (low + 1 < high) {
            mid = (low + high) / 2;
            if (cal(mid) >= m) {
                low = mid;
            } else {
                high = mid;
            }
        }

        sb.append(low);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long cal(int mid) {
        long sum = 0;

        for (int i = 0; i < n; i++) {
            if (tree[i] > mid) {
                sum += (tree[i] - mid);
            }
        }

        return sum;
    }

}
