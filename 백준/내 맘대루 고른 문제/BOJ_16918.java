import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16918 {
    static int r,c,n;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i=0; i<r; i++){
            char[] ch = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                map[i][j] = -1;
                if(ch[j] == 'O'){
                    map[i][j] = 0;
                } else {
                    if(n > 1) {
                        map[i][j] = 2;
                    }
                }
            }
        }

        int time = 3;

        while(time < n+1){
            if(time % 2 == 1){
                for(int i=0; i<r; i++){
                    for(int j=0; j<c; j++){
                        if(map[i][j] == time - 3){
                            map[i][j] = -1;
                            for(int k=0; k<4; k++){
                                int nx = i + dx[k];
                                int ny = j + dy[k];

                                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                                if(map[nx][ny] == time-3) continue;

                                map[nx][ny] = -1;
                            }
                        }
                    }
                }
            } else {
                for(int i=0; i<r; i++){
                    for(int j=0; j<c; j++){
                        if(map[i][j] == -1){
                            map[i][j] = time;
                        }
                    }
                }
            }
            time++;
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] != -1){
                    sb.append("O");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
