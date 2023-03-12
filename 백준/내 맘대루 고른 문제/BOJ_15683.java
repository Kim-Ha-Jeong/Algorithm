import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int origin = 0, ans = 0, copy;
    static boolean[][] v;
    static ArrayList<Node> cctv = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) cctv.add(new Node(i,j,map[i][j]));
                if(map[i][j] == 0) origin++;
            }
        }

        ans = origin;
        int[] dir = new int[cctv.size()];
        combination(0, dir);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int[] dir){
        if(depth == cctv.size()){
            ans = Math.min(ans, calc(dir));
            return;
        }

        Node now = cctv.get(depth);
        if(now.type == 2){
            for(int i=0; i<2; i++){
                dir[depth] = i;
                combination(depth+1, dir);
            }
        } else if(now.type == 5){
            dir[depth] = 0;
            combination(depth+1, dir);
        } else {
            for(int i=0;  i<4; i++){
                dir[depth] = i;
                combination(depth+1, dir);           }
        }
    }

    static void solve(int nx, int ny, int d){
        while(true){
            nx += dx[d];
            ny += dy[d];

            if(nx < 0|| nx >= n || ny < 0 || ny >= m) break;
            if(map[nx][ny] == 6) break;
            if(map[nx][ny] == 0 && !v[nx][ny]){
                v[nx][ny] = true;
                copy--;
            }
        }
    }

    static int calc(int[] dir){
        copy = origin;
        v = new boolean[n][m];

        for(int i=0; i<dir.length; i++){
            Node cur = cctv.get(i);
            int d = dir[i];

            solve(cur.x, cur.y, d);
            if(cur.type == 2){
                solve(cur.x, cur.y, (d+2)%4);
            } else if(cur.type == 3){
                solve(cur.x, cur.y, (d+1)%4);
            } else if(cur.type == 4){
                solve(cur.x, cur.y, (d+1)%4);
                solve(cur.x, cur.y, (d+2)%4);
            } else if(cur.type == 5){
                for(int j=1; j<4; j++){
                    solve(cur.x, cur.y, j);
                }
            }
        }

        return copy;
    }



    static class Node {
        int x;
        int y;
        int type;

        Node(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type =type;
        }
    }
}
