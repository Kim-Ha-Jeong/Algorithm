import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_25797 {
    static int n;
    static Node[][] map;
    static boolean[][][] block;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] part;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new Node[n][n];
        block = new boolean[n][n][4];
        part = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = new Node(Integer.parseInt(st.nextToken()));
            }
        }

        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int number = Integer.parseInt(st.nextToken());

            map[r][c].num = number;
        }

        for(int i=0; i<2*n+1; i++){
            char[] ch = br.readLine().toCharArray();
            if(i == 0 || i == 2*n) continue;
            for(int j=0; j<2*n+1; j++){
                if(j == 0 || j == 2*n) continue;
                if(i % 2 == 1){
                    if(ch[j] == '|'){
                        int tmp = j/2-1;
                        block[i/2][tmp][1] = true;
                        block[i/2][tmp+1][3] = true;
                    }
                } else {
                    if(ch[j] == '-'){
                        int row = i/2-1;
                        int col = j/2;
                        block[row][col][2] = true;
                        block[row+1][col][0] = true;
                    }
                }
            }
        }

        int cnt = 1;
        boolean flag = true;
        for(int i=0; i<n; i++){
            if(!flag) break;
            for(int j=0; j<n; j++){
                if(part[i][j] != 0) continue;
                flag &= bfs(i,j,cnt++);
                if(!flag) break;
            }
        }


        bw.write(flag ? "1" : "0");
        bw.flush();
        bw.close();

    }

    static boolean bfs(int x, int y, int num){
        Queue<Loca> q = new LinkedList<>();
        ArrayList<Loca>[] group = new ArrayList[2];
        group[0] = new ArrayList<>();
        group[1] = new ArrayList<>();

        int count = 0;
        int[] color = new int[2];
        Loca l = new Loca(x,y,map[x][y].color);
        color[l.color]++;
        part[x][y] = num;
        if(map[x][y].num != -1) count++;
        group[l.color].add(l);
        q.add(l);

        while(!q.isEmpty()){
            Loca now = q.poll();
            int tmp = 0;
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny <0 || ny >= n) continue;
                if(block[now.x][now.y][i] || part[nx][ny] != 0) continue;

                Loca next = new Loca(nx,ny,map[nx][ny].color);
                color[next.color]++;
                q.add(next);
                if(map[nx][ny].num != -1) count++;
                if(now.color != next.color) tmp++;
                group[next.color].add(next);
                part[nx][ny] = num;
            }
        }

        if((color[0] != color[1]) || (color[0] == 0 || color[1] == 0)) return false;
        if(count >= 2) return false;

        int a = isSetApart(group[0].get(0));
        int b = isSetApart(group[1].get(0));
        if((a + b) != (color[0] + color[1])) return false;
        if(!isInLine(x,y)) return false;
        if(!same(group[0], group[1])) return false;

        return true;
    }

    static int isSetApart(Loca first){
        Queue<Loca> q = new LinkedList<>();
        int cnt = 1;
        boolean[][] v = new boolean[n][n];
        v[first.x][first.y] = true;
        q.add(first);

        while(!q.isEmpty()){
            Loca now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx>=n || ny < 0 || ny >=n) continue;
                if(v[nx][ny] || block[now.x][now.y][i]) continue;
                if(map[nx][ny].color == now.color && part[nx][ny] == part[now.x][now.y]){
                    q.add(new Loca(nx,ny,now.color));
                    v[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean isInLine(int x, int y){
        Queue<Loca> q = new LinkedList<>();
        boolean[][] v = new boolean[n][n];
        q.add(new Loca(x,y,-1));
        v[x][y] = true;

        while(!q.isEmpty()){
            Loca now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y  +dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(part[now.x][now.y] == part[nx][ny]) {
                    if(block[now.x][now.y][i]) return false;
                } else {
                    continue;
                }
                if(v[nx][ny]) continue;
                q.add(new Loca(nx,ny,-1));
                v[nx][ny] = true;
            }
        }

        return true;
    }

    static boolean same(ArrayList<Loca> white, ArrayList<Loca> gray){
        if(white.size() == 1 && gray.size() == 1) return true;
        Collections.sort(white);
        Collections.sort(gray);

        int X = gray.get(0).x;
        int Y = gray.get(0).y;

        for(int j=0; j<gray.size(); j++){
            gray.get(j).x -= X;
            gray.get(j).y -= Y;
        }

        for(int i=0; i<4; i++){
            int zeroX = white.get(0).x;
            int zeroY = white.get(0).y;

            for(int j=0; j<white.size(); j++){
                white.get(j).x -= zeroX;
                white.get(j).y -= zeroY;
            }

            boolean isRight = true;

            for(int j=0; j<gray.size(); j++){
                Loca g = gray.get(j);
                Loca w = white.get(j);

                if(g.x != w.x || g.y != w.y){
                    isRight = false;
                    break;
                }
            }

            if(isRight) return true;
            else {
                for(int j=0; j<white.size(); j++){
                    int tmp = white.get(j).x;

                    white.get(j).x = white.get(j).y;
                    white.get(j).y = -tmp;
                }
                Collections.sort(white);
            }
        }

        return false;
    }

    static class Loca implements Comparable<Loca>{
        int x;
        int y;
        int color;

        Loca(int x, int y, int color){
            this.x = x;
            this.y = y;
            this.color = color;
        }

        @Override
        public int compareTo(Loca l){
            if(this.x == l.x) return this.y - l.y;
            return this.x - l.x;
        }
    }

    static class Node {
        int color;
        int num = -1;

        Node(int color){
            this.color = color;
        }
    }
}
