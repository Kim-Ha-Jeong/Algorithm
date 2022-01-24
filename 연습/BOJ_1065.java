import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1065 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int ans = 0;

        for (int i = 1; i < n + 1; i++) {
            char[] c = Integer.toString(i).toCharArray();

            if (c.length == 3) {
                if (c[1] - c[0] == c[2] - c[1])
                    ans++;
            } else if (c.length < 3)
                ans++;
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
