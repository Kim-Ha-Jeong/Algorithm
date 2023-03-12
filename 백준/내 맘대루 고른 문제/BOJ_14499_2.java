import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14499_2 {
    static int n,m,x,y,k;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[7];

        StringBuffer sb = new StringBuffer();
        int nx = x, ny = y;
        st = new StringTokenizer(br.readLine());
        while(k-- > 0){
            int dir = Integer.parseInt(st.nextToken());

            if(nx + dx[dir] < 0 || nx + dx[dir] >= n || ny + dy[dir] < 0 || ny + dy[dir] >= m) continue;
            roll(dir);
            nx += dx[dir];
            ny += dy[dir];

            if(map[nx][ny] == 0){
                map[nx][ny] = dice[6];
            } else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            sb.append(dice[1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void roll(int dir){
        int tmp;
        if(dir == 1){
            tmp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = tmp;
        } else if(dir == 2){
            tmp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else if(dir == 3){
            tmp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        } else {
            tmp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }
    }
}
