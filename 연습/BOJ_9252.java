import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_9252 {
    static String A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        A = br.readLine();
        B = br.readLine();

        int aLen = A.length();
        int bLen = B.length();
        int[][] dp = new int[aLen + 1][bLen + 1];
        int[][] route = new int[aLen + 1][bLen + 1];

        for (int i = 1; i < aLen + 1; i++) {
            for (int j = 1; j < bLen + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    route[i][j] = 1;
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        route[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        route[i][j] = 3;
                    }
                }
            }
        }

        int nx = aLen, ny = bLen;
        while (nx >= 1 && ny >= 1) {
            if (route[nx][ny] == 1) {
                sb.append(A.charAt(nx - 1));
                nx--;
                ny--;
            } else if (route[nx][ny] == 2) {
                nx--;
            } else if (route[nx][ny] == 3) {
                ny--;
            }
        }

        bw.write(sb.length() + "\n");
        bw.write(sb.reverse().toString());
        bw.flush();
        bw.close();
    }

}
