import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
    static int n,l,r;
    static int[][] people;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        people = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(true){
            int cnt = 0;
            v = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(v[i][j]) continue;
                    cnt += bfs(i,j);
                }
            }

            if(cnt == 0) break;
            day++;
        }

        bw.write(day+"");
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> list = new ArrayList<>();
        q.add(new Node(x,y));
        v[x][y] = true;
        list.add(new Node(x,y));

        int sum = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();
            sum += people[cur.x][cur.y];

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(v[nx][ny]) continue;

                int sub = Math.abs(people[cur.x][cur.y] - people[nx][ny]);

                if(sub < l || sub > r) continue;

                Node next = new Node(nx,ny);
                q.add(next);
                list.add(next);
                v[nx][ny] = true;
            }
        }

        if(list.size() == 1) return 0;
        int avg = sum / list.size();

        for(Node cur : list){
            people[cur.x][cur.y] = avg;
        }

        return list.size();
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
