import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2174 {
    static int a,b;
    static int[][] map;
    static String[] command;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static HashMap<Integer,Node> robot = new HashMap<Integer, Node>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[b][a];

        int idx = 1;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);
            int d = -1;

            switch(c){
                case 'N':
                    d = 0;
                    break;
                case 'E':
                    d = 1;
                    break;
                case 'S':
                    d = 2;
                    break;
                case 'W':
                    d = 3;
                    break;
            }

            map[x][y] = idx;
            robot.put(idx, new Node(x,y,d));
            idx++;
        }

        command = new String[m];
        for(int i=0; i<m; i++){
            command[i] = br.readLine();
        }

        boolean complete = true;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<m; i++){
            String[] str = command[i].split(" ");
            int num = Integer.parseInt(str[0]);
            char com = str[1].charAt(0);
            int dist = Integer.parseInt(str[2]);

            Node now = robot.get(num);
            int nd = now.d;

            if(com == 'R') {
                nd = (nd + dist) % 4;
            }
            else if(com == 'L') {
                dist %= 4;
                nd = (nd - dist) < 0 ? 4 + (nd- dist) : nd - dist;
            }
            else if(com == 'F') {
                int flag = 0;
                int r = -1;
                map[now.x][now.y] = 0;
                for (int j = 0; j < dist; j++) {
                    int nx = now.x + dx[nd];
                    int ny = now.y + dy[nd];

                    if (nx < 0 || nx >= b || ny < 0 || ny >= a) {
                        flag = 1;
                        break;
                    }

                    if (map[nx][ny] > 0) {
                        flag = 2;
                        r = map[nx][ny];
                        break;
                    }
                    now.x = nx;
                    now.y = ny;
                }

                if (flag > 0) {
                    if (flag == 1) {
                        sb.append("Robot ").append(num).append(" crashes into the wall");
                    } else if (flag == 2) {
                        sb.append("Robot ").append(num).append(" crashes into robot ").append(r);
                    }
                    complete = false;
                    break;
                }
            }
            map[now.x][now.y] = num;
            robot.put(num, new Node(now.x,now.y,nd));
        }

        if(complete){
            sb.append("OK");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
