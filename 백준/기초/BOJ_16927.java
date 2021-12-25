import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_16927 {
    static int n, m, r;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
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

        int rowStart = 0, rowEnd = n - 1, colStart = 0, colEnd = m - 1;
        while (true) {
            int size = (rowEnd - rowStart + 1) * 2 + (colEnd - colStart + 1) * 2 - 4;
            move(rowStart, rowEnd, colStart, colEnd, r % size);
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
            if (rowStart > rowEnd || colStart > colEnd)
                break;
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

    static void move(int rowStart, int rowEnd, int colStart, int colEnd, int cnt) {
        for (int k = 0; k < cnt; k++) {
            int tmp = arr[rowStart][colStart];

            for (int i = colStart; i < colEnd; i++) {
                arr[rowStart][i] = arr[rowStart][i + 1];
            }

            for (int i = rowStart; i < rowEnd; i++) {
                arr[i][colEnd] = arr[i + 1][colEnd];
            }

            for (int i = colEnd; i > colStart; i--) {
                arr[rowEnd][i] = arr[rowEnd][i - 1];
            }

            for (int i = rowEnd; i > rowStart; i--) {
                arr[i][colStart] = arr[i - 1][colStart];
            }

            arr[rowStart + 1][colStart] = tmp;
        }
    }
}
