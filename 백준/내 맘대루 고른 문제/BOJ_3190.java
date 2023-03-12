import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_3190 {
    static int n, d = 1;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int k = Integer.parseInt(br.readLine());

        while(k-- > 0){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x-1][y-1] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        int[][] command = new int[l][2];
        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken());
            command[i][1] = st.nextToken().charAt(0) == 'D' ? 1 : -1;
        }

        int second = 0;
        ArrayDeque<Node> snake = new ArrayDeque<Node>();
        snake.add(new Node(0,0));
        map[0][0] = -1;
        int idx = 0;
        while(true){
            Node now = snake.peekLast();
            int nx = now.x + dx[d];
            int ny = now.y + dy[d];

            if(nx < 0 || nx >= n || ny <0 || ny >= n) {
                break;
            }

            if(map[nx][ny] == -1){
                break;
            } else {
                if(map[nx][ny] == 0){
                    Node tail = snake.pollFirst();
                    map[tail.x][tail.y] = 0;
                }
                snake.add(new Node(nx,ny));
                map[nx][ny] = -1;
            }

            second++;
            if(idx < l && command[idx][0] == second){
                if(command[idx][1] == -1){
                    d = d == 0 ? 3 : d-1;
                } else {
                    d = (d+1)%4;
                }
                idx++;
            }
        }

        bw.write((second+1)+"");
        bw.flush();
        bw.close();
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
