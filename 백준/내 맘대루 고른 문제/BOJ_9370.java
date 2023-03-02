import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ_9370 {
    static int n,m,t,s,g,h;
    static int[] end;
    static ArrayList<Node>[] con;
    static TreeSet<Integer> ans;

    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            ans = new TreeSet<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            con = new ArrayList[n+1];
            for(int i=1; i<n+1; i++){
                con[i] = new ArrayList<>();
            }

            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                d *= 2;
                if((a == h && b == g) || (a == g && b == h)) d-=1;
                con[a].add(new Node(b,d));
                con[b].add(new Node(a,d));
            }

            end = new int[t];
            for(int i=0; i<t; i++){
                end[i] = Integer.parseInt(br.readLine());
            }

            for(int i=0; i<t; i++){
                dijkstra(s, end[i]);
            }

            for(int tmp : ans){
                sb.append(tmp).append(" ");
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(start,0));
        dist[start] = 0;
        int min = -1;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.idx == end){
                min = cur.cost;
                break;
            }

            if(cur.cost > dist[cur.idx]) continue;

            for(Node next : con[cur.idx]){
                if(dist[next.idx] > cur.cost + next.cost){
                    dist[next.idx] = cur.cost + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        if(min != -1){
            if(min % 2 == 1){
                ans.add(end);
            }
        }
    }

    static class Node implements Comparable<Node>{
        int idx;
        int cost;

        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
}
