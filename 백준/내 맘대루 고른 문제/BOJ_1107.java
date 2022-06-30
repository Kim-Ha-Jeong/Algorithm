import java.io.*;
import java.util.*;

public class BOJ_1107 {
    static boolean[] broken;
    static int n, ans;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = sc.nextInt();
        int m = sc.nextInt();

        broken = new boolean[10];

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            broken[x] = true;
        }

        ans = Math.abs(n - 100);

        for (int i = 0; i < 1000000; i++) {
            String num = String.valueOf(i);
            boolean flag = true;
            for (int j = 0; j < num.length(); j++) {
                if (broken[num.charAt(j) - '0']) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                continue;
            int result = Math.abs(i - n) + num.length();
            ans = Math.min(ans, result);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        sc.close();
    }

}
