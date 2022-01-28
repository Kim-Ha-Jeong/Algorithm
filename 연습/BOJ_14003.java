import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_14003 {
    static int[] arr, dp, idx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        idx = new int[n + 1];
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];
        idx[1] = 1;
        int len = 1;

        for (int i = 2; i < n + 1; i++) {
            if (arr[i] > dp[len]) {
                len++;
                idx[i] = len;
                dp[len] = arr[i];
            } else {
                int tmp = binarySearch(1, len, arr[i]);
                dp[tmp] = arr[i];
                idx[i] = tmp;
            }
        }

        sb.append(len).append("\n");

        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            if (idx[i] == len) {
                ret.add(arr[i]);
                len--;
            }
            if (len < 1)
                break;
        }

        for (int i = ret.size() - 1; i >= 0; i--) {
            sb.append(ret.get(i)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

}
