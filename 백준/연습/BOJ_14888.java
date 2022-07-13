import java.util.*;
import java.io.*;

public class BOJ_14888 {
    static int[] result;
    static int[] arr;
    static int[] op = new int[4];
    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        result = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);

        sb.append(max).append("\n");
        sb.append(min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        if (depth == n) {
            int num = calc();
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] <= 0)
                continue;
            op[i]--;
            result[depth] = i;
            dfs(depth + 1);
            op[i]++;
        }
    }

    static int calc() {
        int ret = arr[0];
        for (int i = 1; i < n; i++) {
            if (result[i] == 0) {
                ret += arr[i];
            } else if (result[i] == 1) {
                ret -= arr[i];
            } else if (result[i] == 2) {
                ret *= arr[i];
            } else {
                if (ret < 0 && arr[i] > 0) {
                    int temp = ret * (-1);
                    ret = (int) (temp / arr[i]) * (-1);
                } else {
                    ret = (int) (ret / arr[i]);
                }
            }
        }

        return ret;
    }

}
