import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2589 {
    static int n,m;
    static char[][] map;
    static int[][] num;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        num = new int[n][m];

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 1;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(num[i][j] > 0 || map[i][j] == 'W') continue;
                ArrayList<Node> list = setNumber(i,j,cnt);
                for(int l=0; l<list.size(); l++){
                    getShort(list.get(l), cnt);
                }
                cnt++;
            }
        }

        bw.write(max+"");
        bw.flush();
        bw.close();
    }

    static void getShort(Node a, int number){
        boolean[][] v = new boolean[n][m];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(a.x,a.y,0));
        v[a.x][a.y] = true;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            max = Math.max(max, now.cnt);

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny= now.y +dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny>= m) continue;
                if(v[nx][ny] || map[nx][ny] == 'W' || num[nx][ny] != number) continue;

                v[nx][ny] = true;
                pq.add(new Node(nx,ny,now.cnt+1));
            }
        }
    }

    static ArrayList<Node> setNumber(int x, int y, int number){
        ArrayList<Node> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y,0));
        num[x][y] = number;

        while(!q.isEmpty()){
            Node now = q.poll();
            list.add(now);

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == 'W' || num[nx][ny] > 0) continue;

                num[nx][ny] = number;
                q.add(new Node(nx,ny, now.cnt+1));
            }
        }

        return list;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt){
            this.x =x;
            this.y =y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n){
            return this.cnt - n.cnt;
        }
    }
}
