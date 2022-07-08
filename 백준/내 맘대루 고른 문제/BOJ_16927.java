import java.util.*;
import java.io.*;

public class BOJ_16927 {
    static int n, m, r;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start = 0;
        int N = n;
        int M = m;
        while (true) {
            if (start > N - 1 || start > M - 1) {
                break;
            }

            int size = (N - start) * 2 + (M - start) * 2 - 4;
            rotate(start, N, M, r % size);

            start++;
            N--;
            M--;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void rotate(int start, int N, int M, int cnt) {
        for (int k = 0; k < cnt; k++) {
            int tmp = arr[start][start];

            for (int i = start + 1; i < M; i++) {
                arr[start][i - 1] = arr[start][i];
            }

            for (int i = start + 1; i < N; i++) {
                arr[i - 1][M - 1] = arr[i][M - 1];
            }

            for (int i = M - 1; i > start; i--) {
                arr[N - 1][i] = arr[N - 1][i - 1];
            }

            for (int i = N - 1; i > start; i--) {
                arr[i][start] = arr[i - 1][start];
            }

            arr[start + 1][start] = tmp;
        }
    }

}
