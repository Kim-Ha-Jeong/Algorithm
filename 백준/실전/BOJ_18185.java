import java.util.*;
import java.io.*;

public class BOJ_18185 {
    static int[] arr;
    static int n, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i + 1] > arr[i + 2]) {
                int two = Math.min(arr[i], arr[i + 1] - arr[i + 2]);
                sum += two * 5;
                arr[i] -= two;
                arr[i + 1] -= two;

                calThree(i);
            } else {
                calThree(i);

                int two = Math.min(arr[i], arr[i + 1]);
                sum += two * 5;
                arr[i] -= two;
                arr[i + 1] -= two;
            }

            sum += 3 * arr[i];
        }

        bw.write(sb.append(sum).toString());
        bw.flush();
        bw.close();

    }

    static void calThree(int i) {
        int three = Math.min(arr[i], Math.min(arr[i + 1], arr[i + 2]));
        sum += three * 7;
        arr[i] -= three;
        arr[i + 1] -= three;
        arr[i + 2] -= three;
    }

}
