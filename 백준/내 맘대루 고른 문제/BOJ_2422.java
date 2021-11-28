import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_2422 {
    static int n, m;
    static int cnt;
    static boolean[] visited;
    static int[][] notMatch;
    static int[] data = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        notMatch = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            notMatch[a][b] = 1;
            notMatch[b][a] = 1;
        }

        combination(0, 1);

        bw.write(cnt + "");
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int start) {
        if (depth == 3) {
            if (isRight())
                cnt++;
            return;
        }

        for (int i = start; i < n + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                data[depth] = i;
                combination(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isRight() {
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (notMatch[data[i]][data[j]] == 1)
                    return false;
            }
        }

        return true;
    }
}
