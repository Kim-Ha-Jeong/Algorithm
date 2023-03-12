import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_15685_2 {
    static int[] dx = {0,-1,0,1};
    static int[] dy ={1,0,-1,0};
    static boolean[][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        v = new boolean[101][101];

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> dir = new ArrayList<>();

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            int nx = x, ny = y;
            dir.add(d);

            nx += dx[d];
            ny += dy[d];

            v[x][y] = v[nx][ny] = true;
            while(g-->0){
                int size = dir.size();

                for(int i=1; i<size+1; i++){
                    int nd = (dir.get(size-i)+1)%4;

                    nx += dx[nd];
                    ny += dy[nd];

                    v[nx][ny] = true;
                    dir.add(nd);
                }
            }
        }

        int ans = 0;
        for(int i=0; i<101; i++){
            if(i+1 > 100) break;
            for(int j=0; j<101; j++){
                if(j+1 > 100) break;
                if(v[i][j] && v[i+1][j] && v[i][j+1] && v[i+1][j+1]){
                    ans++;
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
