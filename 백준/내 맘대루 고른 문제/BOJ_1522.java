import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1522 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] ch = br.readLine().toCharArray();
        int n = ch.length;

        int window = 0;
        for(int i=0; i<n; i++){
            if(ch[i] == 'a') window++;
        }

        int ans = 0, cnt = 0;

        if(window > 1 && window != n) {
            for (int i = 0; i < window; i++) {
                if (ch[i] == 'b') {
                    ans++;
                    cnt++;
                }
            }

            int start = 0;
            int end = window;

            while (start != n) {
                if (ch[start] == 'b') cnt--;
                if (ch[end] == 'b') cnt++;

                start++;
                end = (end + 1) % n;
                ans = Math.min(ans, cnt);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }
}
