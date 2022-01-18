import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1806 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] num = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int sum = num[0], low = 0, high = 0, ans = Integer.MAX_VALUE;

        while (true) {
            if (high == n)
                break;
            if (sum < s) {
                sum += num[++high];
            } else {
                ans = Math.min(ans, high - low + 1);
                sum -= num[low++];
            }
        }

        if (ans == Integer.MAX_VALUE)
            sb.append(0);
        else
            sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
