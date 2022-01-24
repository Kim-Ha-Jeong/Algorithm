import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1103 {
    static int n, m;
    static char[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean flag = true;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }

        visited[0][0] = true;
        dfs(0, 0, 1);

        if (!flag)
            sb.append(-1);
        else {
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int x, int y, int cnt) {
        dp[x][y] = cnt;
        ans = Math.max(cnt, ans);

        int num = map[x][y] - '0';
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * num;
            int ny = y + dy[i] * num;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            // db[nx][ny] > dp[x][y]면 방문 X
            // 왜냐면 이미 큰 곳으로 갈 이유가 없음 (반복횟수만 늘어남)
            if (cnt < dp[nx][ny] || map[nx][ny] == 'H') {
                continue;
            }

            // dp[nx][ny] != 0 을 대신 사용하지 못하는 이유
            // 사이클이 생성이 아니고 다른 가지의 dp 값이 저장되어 있는 경우도 있음
            if (visited[nx][ny]) {
                flag = false;
                return;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1);
            visited[nx][ny] = false;
        }

    }
}
