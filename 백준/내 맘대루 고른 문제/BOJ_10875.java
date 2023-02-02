import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_10875 {
    static int n, sec = 0, ans = 0;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] command;
    static ArrayDeque<Node> snake = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int l = Integer.parseInt(br.readLine());
        n = 2*l+1;
        map = new int[n][n];

        int m = Integer.parseInt(br.readLine());
        command = new int[m][2];

        sec = 0;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            sec += Integer.parseInt(st.nextToken());
            command[i][0] = sec;
            command[i][1] = st.nextToken().charAt(0) == 'L' ? 0 : 1;
        }

        map[l][l] = -1;
        snake.add(new Node(l, l));

        move();

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void move(){
        int time = 1;
        int d = 1;
        int idx = 0;

        while(true){
            Node now = snake.getLast();

            if(idx < command.length && command[idx][0] == time){
                if(command[idx][1] == 0){
                    d = d == 0 ? 3 : d-1;
                } else {
                    d = (d+1)%4;
                }
                idx++;
            }

            int nx = now.x + dx[d];
            int ny = now.y + dy[d];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == -1) {
                ans = time+1;
                return;
            }

            snake.add(new Node(nx,ny));
            map[nx][ny] = -1;
            time++;
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
