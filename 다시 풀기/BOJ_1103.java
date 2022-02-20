import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_1103 {
    static int r, c;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] visited;
    static int[][] dp;
    static char[][] map;
    static int ans = 1;
    static boolean flag = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        dp = new int[r][c];

        for (int i = 0; i < r; i++) {
            char[] charcter = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = charcter[j];
            }
        }

        dfs(0, 0, 1);

        if (!flag)
            sb.append(-1);
        else
            sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int x, int y, int cnt) {
        dp[x][y] = cnt;
        ans = Math.max(ans, cnt);

        int mul = map[x][y] - '0';
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * mul;
            int ny = y + dy[i] * mul;

            if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                continue;

            if (cnt < dp[nx][ny] || map[nx][ny] == 'H')
                continue;

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
