import java.util.*;
import java.io.*;

public class BOJ_1759 {
    static StringBuffer sb = new StringBuffer();
    static char[] ch;
    static int l, c;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        ch = new char[c];

        for (int i = 0; i < c; i++) {
            ch[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(ch);
        dfs(0, 0, 0, "", 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int vow, int cons, String str, int idx) {
        if (depth == l) {
            if (vow >= 1 && cons >= 2) {
                sb.append(str).append("\n");
            }
            return;
        }

        for (int i = idx; i < c; i++) {
            boolean flag = distinguish(ch[i]);
            if (flag)
                dfs(depth + 1, vow + 1, cons, str + ch[i], i + 1);
            else
                dfs(depth + 1, vow, cons + 1, str + ch[i], i + 1);
        }
    }

    static boolean distinguish(char tmp) {
        if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'u' || tmp == 'o')
            return true;
        else
            return false;
    }
}
