import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460 {
    static int r,c, ans = -1;
    static char[][] map;
    static boolean[][][][] v;

    static int[] dx ={0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        v = new boolean[r][c][r][c];
        map = new char[r][c];

        int rx,ry,bx,by ;
        bx = rx = ry=by = -1;
        for(int i=0; i<r; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                map[i][j] = tmp[j];
                if(map[i][j] == 'B'){
                    bx = i; by = j;
                }
                if(map[i][j] == 'R'){
                    rx = i;
                    ry = j;
                }
            }
        }

        bfs(rx,ry,bx,by);

        bw.write(sb.append(ans > 10 ? -1 : ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int srx, int sry, int sbx, int sby){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(srx,sry,sbx,sby,0,map));
        v[srx][sry][sbx][sby] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.cnt >= 11) return;

            int rx = now.rx;
            int ry = now.ry;
            int bx = now.bx;
            int by = now.by;
            Marble red = new Marble(-1,-1);
            Marble blue = new Marble(-1,-1);

            for(int d=0; d<4; d++){
                char[][] tmp = copy(now.map);
                if(d == 0){
                    if(ry > by){
                        red = go(rx, ry, 'R',d, tmp);
                        blue = go(bx,by,'B',d, tmp);
                    } else {
                        blue = go(bx,by,'B',d, tmp);
                        red = go(rx,ry,'R',d, tmp);
                    }
                } else if(d == 1){
                    if(ry < by){
                        red = go(rx, ry, 'R',d, tmp);
                        blue = go(bx,by,'B',d, tmp);
                    } else {
                        blue = go(bx,by,'B',d, tmp);
                        red = go(rx,ry,'R',d, tmp);
                    }
                } else if(d == 2){
                    if(rx > bx){
                        red = go(rx, ry, 'R',d, tmp);
                        blue = go(bx,by,'B',d, tmp);
                    } else {
                        blue = go(bx,by,'B',d, tmp);
                        red = go(rx,ry,'R',d, tmp);
                    }
                } else {
                    if(rx < bx){
                        red = go(rx, ry, 'R',d, tmp);
                        blue = go(bx,by,'B',d, tmp);
                    } else {
                        blue = go(bx,by,'B',d, tmp);
                        red = go(rx,ry,'R',d, tmp);
                    }
                }

                if(red.flag && !blue.flag){
                    ans = now.cnt+1;
                    return;
                } else if(!red.flag && !blue.flag) {
                    if (v[red.x][red.y][blue.x][blue.y]) continue;
                    v[red.x][red.y][blue.x][blue.y] = true;
                    q.add(new Node(red.x, red.y, blue.x, blue.y, now.cnt + 1, tmp));
                }
            }
        }
    }

    static char[][] copy(char[][] m){
        char[][] tmp = new char[r][c];

        for(int i=0; i<r; i++){
            System.arraycopy(m[i], 0, tmp[i], 0, c);
        }

        return tmp;
    }


    static Marble go(int x, int y, char ch, int d, char[][] map){
        map[x][y] = '.';
        boolean flag = false;
        while(true){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] != '.'){
                if(map[nx][ny] == 'O'){
                    x = nx;
                    y = ny;
                    flag = true;
                }
                break;
            }
            x = nx;
            y = ny;
        }
        if(!flag) map[x][y] = ch;
        Marble ret = new Marble(x,y);
        ret.flag = flag;
        return ret;
    }

    static class Marble {
        int x;
        int y;
        boolean flag = false;

        Marble(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;
        char[][] map;

        Node(int rx, int ry, int bx, int by, int cnt, char[][] map){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
            this.map = map;
        }
    }
}

