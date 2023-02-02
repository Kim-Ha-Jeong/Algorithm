import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_5852 {
    static int n, m;
    static int cow = 0;
    static char[][] map;
    static int[][] num;
    static int ans = -1;
    static Node first = new Node(-1,-1);
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] road;
    static int[][] dp;
    static int max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        num = new int[n][m];

        for(int i=0; i<n; i++){
            char[] c = br.readLine().toCharArray();
            for(int j=0; j<m; j++){
                map[i][j] = c[j];
            }
        }

        int cnt = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(num[i][j] == 0 && map[i][j] =='X') {
                    setIslandNumber(i,j,cnt++);
                }
            }
        }

        road = new int[cnt][cnt];

        for(int i=1; i<cnt; i++){
            for(int j=1; j<cnt; j++){
                road[i][j] = Integer.MAX_VALUE;
                if(i == j) road[i][j] = 0;
            }
        }

        int start = 1;
        boolean[] already = new boolean[cnt];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 'X' && !already[num[i][j]]){
                    already[num[i][j]] = true;
                    makeRoad(i,j,num[i][j]);
                }
            }
        }

        for(int i=1; i<cnt; i++){
            max = (max | (1 << i));
        }

        dp = new int[1<<cnt][cnt];
        ans = swim(0,0, cnt);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static int swim(int visit, int idx, int cnt){
        if(dp[visit][idx] != 0) return dp[visit][idx];
        if(max == visit) return dp[visit][idx] = 0;
        int ret = Integer.MAX_VALUE;
        for(int next = 1; next < cnt; next++){
            if(idx == next || (visit & (1 << next)) != 0) continue;
            ret = Math.min(ret, swim((visit | (1 << next)), next, cnt) + road[idx][next]);
        }
        return dp[visit][idx] = ret;
    }

    static void makeRoad(int x,int y, int number){
        int[][] check = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(check[i], Integer.MAX_VALUE);
        }
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x,y));
        visited[x][y] = true;
        check[x][y] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nd = now.cnt;

                if(nx < 0 || nx >= n|| ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || map[nx][ny] == '.') continue;
                if(map[nx][ny] == 'S') nd++;

                if(check[nx][ny] <= nd) continue;

                if(num[nx][ny] > 0){
                    road[number][num[nx][ny]] = Math.min(nd, road[number][num[nx][ny]]);
                }
                check[nx][ny] = nd;
                visited[nx][ny] = true;
                Node next = new Node(nx,ny);
                next.cnt = nd;
                pq.add(next);
            }
        }
    }

    static void setIslandNumber(int x, int y, int cnt){
        boolean[][] v = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        v[x][y] = true;
        num[x][y] = cnt;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny>= m) continue;
                if(v[nx][ny] || map[nx][ny] != 'X') continue;
                q.add(new Node(nx,ny));
                v[nx][ny] = true;
                num[nx][ny] = cnt;
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cow = 0;
        int cnt = 0;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node n){
            return this.cnt - n.cnt;
        }
    }
}
