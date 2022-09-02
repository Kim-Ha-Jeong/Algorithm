import java.io.*;
import java.util.*;

public class BOJ_2003 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int front = 0, back = 0;
        int sum = 0, cnt = 0;
        while (true) {
            if (front >= n || (back >= n && sum < m))
                break;

            if (sum >= m) {
                if (sum == m)
                    cnt++;
                sum -= arr[front++];
            } else {
                sum += arr[back++];
            }
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }
}