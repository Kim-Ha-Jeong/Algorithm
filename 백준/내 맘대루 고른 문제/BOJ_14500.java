import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static int n,m;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[] wdx = {1,1,1,2};
    static int[] wdy = {-1,0,1,0};
    static boolean[][] v;
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new boolean[n][m];

        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                v[i][j] = true;
                dfs(i,j, 0,map[i][j]);
                wing(i,j);
                v[i][j] = false;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void wing(int x, int y){
        int sum = map[x][y];

        if(x < n-2 && y < m-1){
            ans = Math.max(ans, sum + map[x+1][y] + map[x+1][y+1] + map[x+2][y]);
        }

        if(x < n-2 && y > 0){
            ans = Math.max(ans, sum + map[x+1][y] + map[x+1][y-1] + map[x+2][y]);
        }

        if(x < n-1 && y < m-2){
            ans = Math.max(ans, sum + map[x][y+1] + map[x][y+2] + map[x+1][y+1]);
        }

        if (x > 0 && y < m - 2) {
            ans = Math.max(ans, sum + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1]);
        }
    }

    static void dfs(int x, int y, int depth, int sum){
        if(depth >= 3){
            if(ans < sum){
                ans = sum;
            }
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(v[nx][ny]) continue;

            v[nx][ny] = true;
            dfs(nx,ny,depth+1, sum + map[nx][ny]);
            v[nx][ny] = false;
        }
    }
}
