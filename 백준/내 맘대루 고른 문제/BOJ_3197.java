import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_3197{
    static Queue<Node> q, water;
    static boolean[][] v;
    static char[][] map;
    static Node[] swan;
    static int r, c;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean flag = true;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        v = new boolean[r][c];
        map = new char[r][c];
        q = new LinkedList<>();
        water = new LinkedList<>();
        swan = new Node[2];

        int idx = 0;
        for(int i=0; i<r; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                map[i][j] = tmp[j];
                if(map[i][j] == 'L'){
                    swan[idx++] = new Node(i,j);
                }
                if(map[i][j] != 'X'){
                    water.add(new Node(i,j));
                }
            }
        }

        q.add(swan[0]);
        v[swan[0].x][swan[0].y] = true;
        int ans = bfs();

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs(){
        int ret = 0;

        while(true){
            Queue<Node> melt = new LinkedList<>();
            while(!q.isEmpty()){
                Node now = q.poll();

                if(now.x == swan[1].x && now.y == swan[1].y){
                    flag = false;
                    break;
                }

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(v[nx][ny]) continue;

                    v[nx][ny] = true;
                    if(map[nx][ny] == 'X'){
                        melt.add(new Node(nx,ny));
                        continue;
                    }

                    q.add(new Node(nx,ny));
                }
            }

            if(!flag) break;

            q = melt;

            int size = water.size();

            for(int k=0; k<size; k++){
                Node now = water.poll();

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny= now.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                    if(map[nx][ny] == 'X'){
                        map[nx][ny] = '.';
                        water.add(new Node(nx,ny));
                    }
                }
            }

            ret++;
        }

        return ret;
    }

    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}