import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {
    static int n, ans;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int idx = 1;
        while(true){
            n = Integer.parseInt(br.readLine());
            ans = 0;

            if(n == 0) break;

            map = new int[n][n];

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("Problem ").append(idx).append(": ");
            sb.append(ans).append("\n");
            idx++;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(){
        boolean[][] v= new boolean[n][n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,map[0][0]));
        v[0][0] = true;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == n-1 && now.y == n-1) {
                ans = now.money;
                return;
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(v[nx][ny]) continue;

                v[nx][ny] =true;
                pq.add(new Node(nx,ny,now.money + map[nx][ny]));
            }
        }
    }
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int money;

        Node(int x, int y, int money){
            this.x = x;
            this.y = y;
            this.money = money;
        }

        @Override
        public int compareTo(Node n){
            return this.money - n.money;
        }
    }

}
