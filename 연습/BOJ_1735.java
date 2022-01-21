import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1735 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ac = Integer.parseInt(st.nextToken());
        int ap = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int bc = Integer.parseInt(st.nextToken());
        int bp = Integer.parseInt(st.nextToken());

        int aGcd = GCD(ac, ap);
        ac /= aGcd;
        ap /= aGcd;

        int bGcd = GCD(bc, bp);
        bc /= bGcd;
        bp /= bGcd;

        int pGcd = GCD(ap, bp);
        int lcm = ap * bp / pGcd;
        int a = lcm / ap;
        int b = lcm / bp;

        int result = ac * a + bc * b;

        int last = GCD(result, lcm);

        sb.append(result / last).append(" ").append(lcm / last);
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
