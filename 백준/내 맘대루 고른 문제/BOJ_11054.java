import java.util.*;
import java.io.*;

public class BOJ_11054 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] front = new int[n];
        int[] back = new int[n];

        for (int i = 0; i < n; i++) {
            front[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && front[i] < front[j] + 1) {
                    front[i] = front[j] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            back[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j] && back[i] < back[j] + 1) {
                    back[i] = back[j] + 1;
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, front[i] + back[i] - 1);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
