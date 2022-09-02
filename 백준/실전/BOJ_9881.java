import java.util.*;
import java.io.*;

public class BOJ_9881 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] height = new int[n];

        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(height);

        int ans = Integer.MAX_VALUE;
        for (int i = height[0]; i < height[n - 1]; i++) {
            int min = i;
            int max = i + 17;

            if (i + 17 > height[n - 1])
                break;

            int sum = 0;
            for (int j = 0; j < n; j++) {
                int tmp = 0;
                if (height[j] < min)
                    tmp = min - height[j];
                if (height[j] > max)
                    tmp = height[j] - max;

                sum += tmp * tmp;
            }

            ans = Math.min(sum, ans);
        }

        ans = ans == Integer.MAX_VALUE ? 0 : ans;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
