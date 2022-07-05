import java.util.*;
import java.io.*;

public class BOJ_1748 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        String tmp = br.readLine();
        long n = Integer.parseInt(tmp);
        int len = tmp.length();

        long ans = 0;
        for (int i = len - 1; i >= 1; i--) {
            long temp = (long) (9 * Math.pow(10, i - 1) * i);
            ans += temp;
        }

        long temp = (long) ((n - Math.pow(10, len - 1) + 1) * len);
        ans += temp;

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
