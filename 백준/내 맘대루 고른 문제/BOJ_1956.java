import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {
    static int v,e;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int max = 200000000;

        int[][] dist = new int[v+1][v+1];
        for(int i=0; i<v+1; i++){
            Arrays.fill(dist[i], max);
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int a= Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }

        for(int mid=1; mid<v+1; mid++){
            for(int start=1; start<v+1; start++){
                for(int end=1; end<v+1; end++){
                    dist[start][end] = Math.min(dist[start][end], dist[start][mid] + dist[mid][end]);
                }
            }
        }

        int ans = max;

        for(int i=1; i<v+1; i++){
            for(int j=i+1; j<v+1; j++){
                if(dist[i][j] == max || dist[j][i] == max) continue;
                ans = Math.min(ans, dist[i][j] + dist[j][i]);
            }
        }

        if(ans == max) ans = -1;

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Node {
        int idx;
        int cost;

        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
}
