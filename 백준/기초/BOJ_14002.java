import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class BOJ_14002 {
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        String[] s = br.readLine().split(" ");

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(s[i - 1]);
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    result = Math.max(dp[i], result);
                }
            }
        }

        sb.append(result).append("\n");

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = n; i >= 1; i--) {
            if (dp[i] == result) {
                list.add(arr[i]);
                result--;
            }
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
