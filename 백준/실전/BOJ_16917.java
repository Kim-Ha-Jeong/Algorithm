import java.util.*;
import java.io.*;

public class BOJ_16917 {
    static int a, b, c, x, y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int ans = Integer.MAX_VALUE;

        int min = Math.min(x, y);
        int max = Math.max(x, y);

        for (int i = 0; i < max + 1; i++) {
            int sum = 0;
            int half = i * 2;
            if (i <= min) {
                sum += (x - i) * a + (y - i) * b + half * c;
            } else {
                if (x > y) {
                    sum += (x - i) * a + half * c;
                } else {
                    sum += (y - i) * b + half * c;
                }
            }
            ans = Math.min(ans, sum);
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
