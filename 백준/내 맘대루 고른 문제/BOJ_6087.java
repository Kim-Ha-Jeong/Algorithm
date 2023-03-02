import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087 {
    static int w,h;
    static char[][] map;
    static int[][][] dist;
    static Node start, end;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        dist = new int[h][w][4];

        for(int i=0; i<h; i++){
            char[] c = br.readLine().toCharArray();
            for(int j=0; j<w; j++){
                map[i][j] = c[j];
                Arrays.fill(dist[i][j], w*h);
                if(map[i][j] == 'C') {
                    if(start == null){
                        start = new Node(i,j,-1,0);
                    } else {
                        end = new Node(i,j,-1,0);
                    }
                }
            }
        }

        dijkstra();

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.x == end.x && cur.y == end.y){
                ans = Math.min(ans,cur.cnt);
                continue;
            }

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == '*' || (cur.d != -1 && Math.abs(cur.d - i) == 2)) continue;

                int nextCnt = (cur.d == -1 || cur.d == i) ? 0 : 1;

                if(dist[nx][ny][i] > cur.cnt + nextCnt){
                    dist[nx][ny][i] = cur.cnt + nextCnt;
                    pq.add(new Node(nx,ny,i,dist[nx][ny][i]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int d;
        int cnt;

        Node(int x, int y, int d,int cnt){
            this.x =x;
            this.y =y;
            this.d = d;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n){
            return this.cnt - n.cnt;
        }
    }
}
