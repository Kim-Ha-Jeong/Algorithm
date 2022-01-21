import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1837_2 {
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

            int num = 0, tmp = 0;

            for (int j = 0; j < p.length; j++) {
                num = 10 * tmp + (p[j] - '0');
                tmp = num % i;
            }

            if (tmp == 0) {
                flag = true;
                sb.append("BAD").append(" ").append(i);
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

}
