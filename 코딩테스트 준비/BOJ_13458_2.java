import java.util.*;
import java.io.*;

public class BOJ_13458_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n, b, c;

        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long sum = n;
        for (int i = 0; i < n; i++) {
            if (arr[i] > b) {
                sum += Math.ceil((arr[i] - b) / (double) c);
            }
        }

        bw.write(sb.append(sum).toString());
        bw.flush();
        bw.close();
    }

}
