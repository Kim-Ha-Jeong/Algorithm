import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_24042 {
    static int n,m;
    static long[] dist;
    static ArrayList<Node> []con;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        con = new ArrayList[n+1];
        dist = new long[n+1];

        for(int i=1; i<n+1; i++){
            con[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            con[a].add(new Node(b,i));
            con[b].add(new Node(a,i));
        }

        Arrays.fill(dist, Long.MAX_VALUE);

        dijkstra();

        bw.write(dist[n]+"");
        bw.flush();
        bw.close();
    }

    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[now.idx] < now.cost) continue;

            for(Node next : con[now.idx]){
                long nextDist;
                if(now.cost <= next.cost) nextDist = next.cost + 1;
                else {
                    nextDist = ((long) Math.ceil(((double)now.cost-next.cost)/m))*m +next.cost + 1;
                }

                if(nextDist < dist[next.idx]){
                    dist[next.idx] = nextDist;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int idx;
        long cost;

        Node(int idx, long cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return Long.compare(this.cost, n.cost);
        }
    }
}
