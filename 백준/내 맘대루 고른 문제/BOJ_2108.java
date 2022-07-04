import java.io.*;
import java.util.*;

public class BOJ_2108 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];
        int[] arr = new int[8002];
        int[] tmp = new int[8002];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            num[i] = x;
            sum += x;
            arr[4000 + x]++;
        }

        Arrays.sort(num);

        System.arraycopy(arr, 0, tmp, 0, arr.length);
        Arrays.sort(tmp);

        boolean flag = true;
        int max = tmp[8001];
        if (n >= 2) {
            if (tmp[8001] == tmp[8000])
                flag = false;
        }

        int cnt = 1;
        int ans = -1;
        for (int i = 0; i < 8002; i++) {
            if (flag && arr[i] == max) {
                ans = i - 4000;
                break;
            } else if (!flag && arr[i] == max) {
                if (cnt == 2) {
                    ans = i - 4000;
                    break;
                }
                cnt++;
            }
        }

        sb.append(Math.round(sum / (double) n)).append("\n");
        sb.append(num[n / 2]).append("\n");
        sb.append(ans).append("\n");
        sb.append(num[n - 1] - num[0]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
