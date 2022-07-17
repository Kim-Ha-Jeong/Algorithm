import java.util.*;
import java.io.*;

public class BOJ_1339 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int[] alpha = new int[26];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int len = word.length();
            char[] c = word.toCharArray();
            for (int j = 0; j < len; j++) {
                alpha[c[j] - 'A'] += Math.pow(10, len - j - 1);
            }
        }

        Integer[] tmp = Arrays.stream(alpha).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Collections.reverseOrder());

        int idx = 0;
        int num = 9;
        int ans = 0;
        while (true) {
            if (tmp[idx] == 0)
                break;
            ans += (num--) * tmp[idx++];
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
