import java.util.*;
import java.io.*;

public class BOJ_16637 {
    static ArrayList<Integer> num = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        char[] c = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                num.add(c[i] - '0');
            } else {
                op.add(c[i]);
            }
        }

        solve(0, num.get(0));

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void solve(int opIdx, int a) {
        if (opIdx >= op.size()) {
            ans = Math.max(a, ans);
            return;
        }

        int ret1 = cal(opIdx, a, num.get(opIdx + 1));
        solve(opIdx + 1, ret1);

        if (opIdx + 1 < op.size()) {
            int b = cal(opIdx + 1, num.get(opIdx + 1), num.get(opIdx + 2));
            int result = cal(opIdx, a, b);
            solve(opIdx + 2, result);
        }
    }

    static int cal(int opIdx, int a, int b) {
        char c = op.get(opIdx);

        switch (c) {
            case '+':
                return a + b;
            case '*':
                return a * b;
            case '-':
                return a - b;
        }

        return 1;
    }

}
