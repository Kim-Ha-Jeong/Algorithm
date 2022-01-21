import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1837 {
    static boolean[] isNotPrime;
    static int k;
    static char[] p;
    static int MAX = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = st.nextToken().toCharArray();
        k = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[MAX + 1];

        findPrime();

        boolean flag = false;
        for (int i = 2; i < k; i++) {
            if (isNotPrime[i])
                continue;

            if (checkIsBad(i)) {
                flag = true;
                sb.append("BAD ").append(i);
                break;
            }
        }

        if (!flag)
            sb.append("GOOD");

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void findPrime() {
        for (int i = 2; i < MAX + 1; i++) {
            if (isNotPrime[i])
                continue;
            for (int j = i * 2; j < MAX + 1; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    static boolean checkIsBad(int x) {
        int r = 0;

        for (int i = 0; i < p.length; i++) {
            r = (r * 10 + (p[i] - '0')) % x;
        }

        if (r == 0)
            return true;
        else
            return false;

    }

}
