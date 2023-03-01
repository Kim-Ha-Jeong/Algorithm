import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_14940 {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] map;
    static int[][] dist;
    static int n,m;
    static Node end;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dist[i], -1);
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) end = new Node(i,j);
                else if(map[i][j] == 0) dist[i][j] = 0;
            }
        }

        dijkstra();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(){
        Queue<Node> q = new LinkedList<>();
        boolean[][] v = new boolean[n][m];
        q.add(new Node(end.x,end.y));
        v[end.x][end.y] = true;
        dist[end.x][end.y] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == 0 || v[nx][ny]) continue;

                q.add(new Node(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                v[nx][ny] = true;
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
}
