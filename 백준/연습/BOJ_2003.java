import java.util.*;
import java.io.*;

public class BOJ_2003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int back = 0, front = 0, sum = 0, cnt = 0;
        while (true) {
            if ((front > n - 1 || sum < m) && back > n - 1)
                break;
            if (sum < m) {
                sum += arr[back++];
            } else if (sum >= m) {
                if (sum == m)
                    cnt++;
                sum -= arr[front++];
            }
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }

}
