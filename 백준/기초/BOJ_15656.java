import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ_15656 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String[] s = br.readLine().split(" ");
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(s[i]));
        }

        n = set.size();
        arr = new int[n];

        int j = 0;
        for (Integer i : set) {
            arr[j++] = i;
        }

        Arrays.sort(arr);

        int[] order = new int[m];
        combination(0, order, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int[] order, StringBuilder sb) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(order[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            order[depth] = arr[i];
            combination(depth + 1, order, sb);
        }
    }

}
