import java.util.*;
import java.io.*;

public class BOJ_1037 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int k = Integer.parseInt(br.readLine());

        if (k == 1) {
            int x = Integer.parseInt(br.readLine());
            sb.append(x * x);
        } else {
            int[] arr = new int[k];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            sb.append(arr[0] * arr[k - 1]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
