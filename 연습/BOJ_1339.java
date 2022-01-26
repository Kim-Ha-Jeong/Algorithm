import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_1339 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] alpha = new int[26];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            int len = c.length;
            for (int j = 0; j < len; j++) {
                alpha[c[j] - 'A'] += (int) Math.pow(10, len - j - 1);
            }
        }

        Arrays.sort(alpha);

        int ans = 0;
        int mul = 9;
        for (int i = 25; i >= 0; i--) {
            if (alpha[i] == 0)
                break;
            ans += alpha[i] * mul--;
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
