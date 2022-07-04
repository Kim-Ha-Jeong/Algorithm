import java.io.*;
import java.util.*;

public class BOJ_2309 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int[] arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += arr[i];
        }

        int a = -1, b = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j)
                    continue;
                if (arr[i] + arr[j] == sum - 100) {
                    a = i;
                    b = j;
                    break;
                }
            }
        }

        arr[a] = arr[b] = 0;

        Arrays.sort(arr);

        for (int i = 2; i < 9; i++) {
            sb.append(arr[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
