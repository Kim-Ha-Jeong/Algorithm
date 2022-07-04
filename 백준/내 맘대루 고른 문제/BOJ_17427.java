import java.util.*;
import java.io.*;

public class BOJ_17427 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        long ans = 0;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j * i < n + 1; j++) {
                ans += i;
            }
        }

        ans += n;

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
