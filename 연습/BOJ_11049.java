import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11049 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int matrixR[] = new int[n + 1];
        int matrixC[] = new int[n + 1];
        int d[][] = new int[n + 2][n + 2];
        final int inf = Integer.MAX_VALUE;

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            matrixR[i] = Integer.parseInt(st.nextToken());
            matrixC[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                d[i][j] = inf;
                for (int k = i; k <= j; k++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k + 1][j] + matrixR[i] * matrixC[k] * matrixC[j]);
                }
            }
        }

        sb.append(d[1][n]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
