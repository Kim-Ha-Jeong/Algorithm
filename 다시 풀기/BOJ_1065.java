import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_1065 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ans = 99;
        int n = Integer.parseInt(st.nextToken());

        if (n < 100) {
            sb.append(n);
        } else {

            for (int i = 100; i <= n; i++) {
                if (cal(Integer.toString(i)))
                    ans++;
            }

            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean cal(String str) {
        char[] c = str.toCharArray();
        int sub = c[0] - c[1];
        int tmp;

        for (int i = 1; i < str.length() - 1; i++) {
            tmp = c[i] - c[i + 1];
            if (sub != tmp)
                return false;
        }

        return true;
    }
}
