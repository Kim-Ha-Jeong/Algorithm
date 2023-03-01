import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2933 {
    static int n,m;
    static char[][] map;
    static char[][] change;
    static int[][] v;
    static int[] command;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

        int k = Integer.parseInt(br.readLine());
        command = new int[k];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++){
            command[i] = n - Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<k; i++){
            breakMineral(i%2, command[i]);
            change = new char[n][m];
            for(int x=0; x<n; x++){
                Arrays.fill(change[x], '.');
            }
            v = new int[n][m];
            int num = 1;
            for(int x=n-1; x>=0; x--){
                for(int y=0; y<m; y++){
                    if(v[x][y] != 0 || map[x][y] == '.') continue;
                    bfs(x,y, num++);
                }
            }
            map = change;
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int x, int y, int num){
        int end = -1;
        ArrayList<Node> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        v[x][y] = num;
        list.add(new Node(x,y));
        if(end < x) end = x;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(v[nx][ny] != 0 || map[nx][ny] == '.') continue;

                Node next = new Node(nx,ny);
                v[nx][ny] = num;
                q.add(next);
                list.add(next);
                if(end < nx) end = nx;
            }
        }

        int dist = 0;
        if(end != n-1) {
            dist = n - end - 1;
            for (Node cur : list) {
                for (int nx = cur.x + 1; nx < n; nx++) {
                    if (change[nx][cur.y] == 'x' && v[nx][cur.y] != num) {
                        if (dist > nx - cur.x - 1) {
                            dist = nx - cur.x - 1;
                        }
                        break;
                    }
                }
            }
        }

            for(Node cur : list){
                change[cur.x+dist][cur.y] = 'x';
            }

    }

    static void breakMineral(int flag, int row){
        if(flag == 0) {
            for (int i = 0; i < m; i++) {
                if (map[row][i] == 'x') {
                    map[row][i] = '.';
                    break;
                }
            }
        } else {
            for(int i=m-1; i>=0; i--){
                if(map[row][i] == 'x'){
                    map[row][i] = '.';
                    break;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y =y;
        }
    }
}
