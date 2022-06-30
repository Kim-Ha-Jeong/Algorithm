import java.io.*;
import java.util.*;

class BOJ_2609 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int gcd, lcm;

        if (b > a) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        gcd = getGCD(a, b);
        lcm = a * b / gcd;

        sb.append(gcd).append('\n').append(lcm);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int getGCD(int a, int b) {
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

}