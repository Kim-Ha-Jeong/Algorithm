import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2824 {
    static int[] a;
    static int[] b;
    static int MAX = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        long answer = 1;

        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1)
                continue;
            for (int j = 0; j < m; j++) {
                if (b[j] == 1)
                    continue;

                int gcd = GCD(a[i], b[j]);
                answer *= gcd;

                if (answer >= MAX)
                    flag = true;

                answer %= MAX;
                a[i] /= gcd;
                b[j] /= gcd;

            }
        }

        String s = String.valueOf(answer);
        int len = s.length();

        if (len < 9 && flag) {
            for (int i = 0; i < 9 - len; i++)
                sb.append("0");
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int GCD(int a, int b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

}
