import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;

public class BOJ_6588 {
    static boolean[] isPrime = new boolean[1000001];
    static int max = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (!isPrime[i])
                continue;
            for (int j = 2; i * j <= max; j++) {
                isPrime[i * j] = false;
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            int[] ans = findAdd(n);
            if (ans[0] == -1)
                sb.append("Goldbach's conjecture is wrong.\n");
            else
                sb.append(n).append(" = ").append(ans[0]).append(" + ").append(ans[1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] findAdd(int n) {
        int[] ret = new int[2];
        Arrays.fill(ret, -1);

        for (int i = 2; i < n - 1; i++) {
            if (isPrime[i] && isPrime[n - i]) {
                ret[0] = i;
                ret[1] = n - i;
                break;
            }
        }
        return ret;
    }
}
