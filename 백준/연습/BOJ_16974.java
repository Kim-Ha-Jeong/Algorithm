import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16974 {
    static long[] hamburger, patty;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        hamburger = new long[n + 1];
        patty = new long[n + 1];

        hamburger[0] = 1;
        patty[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            hamburger[i] = 2 * hamburger[i - 1] + 3;
            patty[i] = 2 * patty[i - 1] + 1;
        }

        long ans = solve(n, x);
        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static long solve(int n, long x) {
        if (n == 0) {
            if (x == 0)
                return 0;
            else if (x == 1)
                return 1;
        }

        if (x == 1)
            return 0;
        else if (x <= hamburger[n - 1] + 1) {
            return solve(n - 1, x - 1);
        } else if (x == hamburger[n - 1] + 2) {
            return patty[n - 1] + 1;
        } else if (x <= 2 * hamburger[n - 1] + 2) {
            return patty[n - 1] + 1 + solve(n - 1, x - (2 + hamburger[n - 1]));
        } else {
            return 2 * patty[n - 1] + 1;
        }
    }

}
