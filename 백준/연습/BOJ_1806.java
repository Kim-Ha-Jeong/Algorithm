import java.util.*;
import java.io.*;

public class BOJ_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int front = 0, back = 0;
        int sum = arr[0];
        int len = Integer.MAX_VALUE;

        while (true) {
            if (back == n)
                break;
            if (sum > s) {
                sum -= arr[front];
                len = Math.min(len, back - front + 1);
                front++;
            } else {
                if (sum == s) {
                    len = Math.min(len, back - front + 1);
                }
                sum += arr[++back];
            }
        }

        if (len == Integer.MAX_VALUE) {
            sb.append(0);
        } else {
            sb.append(len);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
