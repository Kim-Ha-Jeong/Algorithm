import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_16926 {
    static int n, m, r;
    static int[][] arr;
    static int[][] copyArr;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        copyArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = Math.min(n, m) / 2;

        for (int i = 0; i < r; i++) {
            left(m);
            up(n, m);
            right(n, m);
            down(n);
            arr = copy(arr, copyArr);
            copyArr = new int[n][m];
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

    static void left(int y) {
        for (int j = 1; j < cnt + 1; j++) {
            for (int i = j; i < y; i++) {
                copyArr[j - 1][i - 1] = arr[j - 1][i];
            }
            y--;
        }
    }

    static void up(int x, int y) {
        for (int j = 1; j < cnt + 1; j++) {
            for (int i = j; i < x; i++) {
                copyArr[i - 1][y - 1] = arr[i][y - 1];
            }
            x--;
            y--;
        }
    }

    static void right(int x, int y) {
        for (int j = 0; j < cnt; j++) {
            for (int i = j; i < y - 1; i++) {
                copyArr[x - 1][i + 1] = arr[x - 1][i];
            }
            x--;
            y--;
        }
    }

    static void down(int x) {
        for (int j = 0; j < cnt; j++) {
            for (int i = j; i < x - 1; i++) {
                copyArr[i + 1][j] = arr[i][j];
            }
            x--;
        }
    }

    static int[][] copy(int[][] target, int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) {
                    target[i][j] = arr[i][j];
                    continue;
                }
                target[i][j] = copy[i][j];
            }
        }

        return target;
    }
}
