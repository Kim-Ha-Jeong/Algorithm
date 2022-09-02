import java.util.*;
import java.io.*;

public class BOJ_11401 {
    static long n, k;
    static long mod = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        long number = factorial(n);

        long denom = factorial(k) * factorial(n - k) % mod;

        long ans = (long) ((number * pow(denom, mod - 2)) % mod);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static long pow(long base, long expo) {
        long result = 1;

        while (expo > 0) {
            if (expo % 2 == 1) {
                result *= base;
                result %= mod;
            }
            base = (base * base) % mod;
            expo /= 2;
        }
        return result;
    }

    static long factorial(long N) {
        long fac = 1;

        while (N > 1) {
            fac = (fac * N) % mod;
            N--;
        }

        return fac;
    }
}