import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_11967 {
    static int n, m;
    static boolean[][] v, light;

    static Queue<Node> lightSave = new LinkedList<>();
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int ans = 1;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new boolean[n][n];
        light = new boolean[n][n];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            ArrayList<Integer> list = new ArrayList<>();
            if(map.containsKey(x*n+y)){
                list = map.get(x*n+y);
            }
            list.add(a*n+b);
            map.put(x*n+y, list);
        }

        light[0][0] = true;
        lightSave.add(new Node(0,0));

        while(!lightSave.isEmpty()){
            Node now = lightSave.poll();
            if(v[now.x][now.y]) continue;
            if(now.x == 0 && now.y == 0) {
                bfs(now.x, now.y);
            } else {
                boolean flag = false;
                for(int d=0; d<4; d++){
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if(v[nx][ny]){
                        flag = true;
                        break;
                    }
                }

                if(flag) bfs(now.x, now.y);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        light[x][y] = v[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            int idx = now.x * n + now.y;
            if(map.containsKey(idx)) {
                ArrayList<Integer> list = map.get(idx);
                for (int turn : list) {
                    int nx = turn / n;
                    int ny = turn % n;

                    if(light[nx][ny]) continue;
                    light[nx][ny] = true;
                    lightSave.add(new Node(nx,ny));
                    ans++;
                }
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny>=n )continue;
                if(v[nx][ny] || !light[nx][ny]) continue;

                q.add(new Node(nx,ny));
                v[nx][ny] = true;
            }
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
