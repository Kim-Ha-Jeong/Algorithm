import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1981 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j],max);
                min = Math.min(map[i][j],min);
            }
        }

        int start = 0;
        int end = max - min;
        int ans = 0;
        while(start <= end){
            int mid = (start + end)/2;
            boolean flag = false;

            for(int i=min; i<max+1; i++){
                if(map[0][0] < i || map[0][0] > i + mid) continue;
                if(bfs(i, i+mid)){
                    flag = true;
                    break;
                }
            }

            if(flag){
                ans = mid;
                end = mid -1;
            } else {
                start = mid+1;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static boolean bfs(int min, int max){
        Queue<Node> q = new LinkedList<>();
        boolean[][] v = new boolean[n][n];
        q.add(new Node(0,0));
        v[0][0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == n-1 && cur.y == n-1) {
                return true;
            }

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >=n ) continue;
                if(map[nx][ny] < min || map[nx][ny] > max) continue;
                if(v[nx][ny]) continue;

                q.add(new Node(nx,ny));
                v[nx][ny] = true;
            }
        }

        return false;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x =x ;
            this.y=y;
        }
    }
}
