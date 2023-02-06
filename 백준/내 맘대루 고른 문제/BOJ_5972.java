import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {
    static int n, m;
    static int ans;
    static ArrayList<Edge>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[start].add(new Edge(end,cost));
            list[end].add(new Edge(start,cost));
        }

        dijkstra();

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.node == n){
                ans = now.cost;
                return;
            }
            if(dist[now.node] < now.cost) continue;

            for(Edge next : list[now.node]){
                if(dist[next.node] > dist[now.node] + next.cost){
                    dist[next.node] = dist[now.node] + next.cost;
                    pq.add(new Edge(next.node, dist[next.node]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        int node;
        int cost;

        Edge(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
}
