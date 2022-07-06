import java.io.*;
import java.util.*;

public class BOJ_15661 {
    static int[] arr;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.print(-1);
            return;
        }

        if (arr[n - 1] > arr[n - 2]) {
            swap(n - 1, n - 2);
        } else {
            boolean flag = true;
            for (int i = n - 1; i >= 1; i--) {
                if (arr[i] > arr[i - 1]) {
                    flag = false;
                    find(i);
                    Arrays.sort(arr, i, n);
                    break;
                }
            }

            if (flag) {
                System.out.print(-1);
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void swap(int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    static void find(int idx) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = idx; i < n; i++) {
            if (arr[idx - 1] < arr[i] && min > arr[i]) {
                min = arr[i];
                index = i;
            }
        }

        swap(index, idx - 1);
    }

}
