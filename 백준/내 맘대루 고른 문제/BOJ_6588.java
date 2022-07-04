import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;

public class BOJ_6588 {
    static boolean[] prime = new boolean[1000001];
    static int MAX = 1000001;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Arrays.fill(prime, true);

        for (int i = 2; i * i < MAX; i++) {
            for (int j = i * i; j < MAX; j += i) {
                prime[j] = false;
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            solve(n);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(int n) {
        boolean flag = false;
        for (int i = 2; i < n + 1; i++) {
            if (prime[i] && prime[n - i]) {
                sb.append(n).append(" = ").append(Math.min(i, n - i)).append(" + ").append(Math.max(i, n - i))
                        .append("\n");
                flag = true;
                break;
            }
        }

        if (!flag)
            sb.append("Goldbach's conjecture is wrong.\n");
    }
}
