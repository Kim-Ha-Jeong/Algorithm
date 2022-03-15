import java.util.*;
import java.io.*;

public class BOJ_14501_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Counsel[] arr = new Counsel[n];
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Counsel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < n; i++) {
            int end = i + arr[i].time;
            if (end <= n) {
                dp[end] = Math.max(dp[end], dp[i] + arr[i].price);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }

        bw.write(sb.append(dp[n]).toString());
        bw.flush();
        bw.close();

    }

    static class Counsel {
        int time;
        int price;

        Counsel(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }

}
