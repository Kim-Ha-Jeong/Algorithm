import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2960 {
    static int n, k;
    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        isPrime = new boolean[n + 1];

        sb.append(primeCheck());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int primeCheck() {
        int cnt = 0;
        for (int i = 2; i < n + 1; i++) {
            if (isPrime[i])
                continue;
            for (int j = i; j < n + 1; j += i) {
                if (isPrime[j])
                    continue;
                isPrime[j] = true;
                if (++cnt == k)
                    return j;
            }
        }

        return -1;
    }

}
