import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
    static int n,m;
    static int[][] map;
    static boolean[][] v;

    static int ans = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static ArrayList<Node> virus;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];
        virus = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new Node(i,j));
            }
        }

        Node[] arr = new Node[3];
        setWall(0,0,arr);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x =x ;
            this.y= y;
        }
    }

    static void setWall(int depth, int idx, Node[] arr){
        if(depth == 3){
            ans = Math.max(ans, bfs(arr));
            return;
        }

        for(int i=idx; i<n*m; i++){
            int x = i / m;
            int y = i % m;
            if(map[x][y] != 0) continue;
            arr[depth] = new Node(x,y);
            setWall(depth+1, i+1, arr);
        }
    }

    static int bfs(Node[] arr){
        int[][] tmp = copy();
        for(Node cur : arr){
            tmp[cur.x][cur.y] = 1;
        }

        boolean[][] v = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();
        q.addAll(virus);

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n|| ny < 0 || ny >= m) continue;
                if(tmp[nx][ny] != 0) continue;
                if(v[nx][ny]) continue;

                q.add(new Node(nx,ny));
                tmp[nx][ny] = 2;
                v[nx][ny] = true;
            }
        }


        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(tmp[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    static int[][] copy(){
        int[][] tmp = new int[n][m];

        for(int i=0; i<n; i++){
            System.arraycopy(map[i],0,tmp[i],0,m);
        }

        return tmp;
    }
}
