import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_2 {
    static int n,m;
    static char[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        dist = new int[n*m+1][n*m+1];

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 'L'){
                    ans = Math.max(ans,bfs(i,j));
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y){
        int ret = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y,0));
        boolean[][] v = new boolean[n][m];
        v[x][y] = true;
        int aIdx = x*n+y;

        while(!q.isEmpty()){
            Node now = q.poll();
            ret = Math.max(now.cnt, ret);

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(v[nx][ny] || map[nx][ny] == 'W') continue;

                q.add(new Node(nx,ny,now.cnt+1));
                v[nx][ny] = true;
            }
        }

        return ret;
    }

    static class Node {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
