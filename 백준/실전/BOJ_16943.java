import java.util.*;
import java.io.*;

public class BOJ_16943 {
    static int ans = -1, n, lenA, lenB, bNum;
    static boolean[] v;
    static String a, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        a = st.nextToken();
        b = st.nextToken();

        lenA = a.length();
        lenB = b.length();

        v = new boolean[lenA];
        bNum = Integer.parseInt(b);

        if (lenA <= lenB)
            permutation(0, "");

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void permutation(int depth, String result) {
        if (depth == lenA) {
            int num = Integer.parseInt(result);
            if (check(result, num) && num < bNum) {
                ans = Math.max(ans, num);
            }
            return;
        }

        for (int i = 0; i < lenA; i++) {
            if (v[i])
                continue;
            v[i] = true;
            permutation(depth + 1, result + a.charAt(i));
            v[i] = false;
        }
    }

    static boolean check(String str, int num) {
        String after = String.valueOf(num);
        return after.length() == str.length();
    }

}
