import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14503 {
    static int n,m;
    static int[][] map;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Node start;
    static int ans = 1;
    static int CLEAN = 3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        map[start.x][start.y] = CLEAN;

        while(!q.isEmpty()){
            Node cur = q.poll();
            Node next = null;

            for(int i=1; i<5; i++){
                int nd = cur.d - i < 0 ? 4 + (cur.d-i) : cur.d - i;
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] != 0) continue;

                next = new Node(nx,ny,nd);
                map[next.x][next.y] = CLEAN;
                ans++;
                break;
            }

            if(next == null){
                int nd = (cur.d + 2)%4;
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                if(map[nx][ny] == 1) break;
                next = new Node(nx,ny,cur.d);
            }

            q.add(next);
        }

    }

    static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
