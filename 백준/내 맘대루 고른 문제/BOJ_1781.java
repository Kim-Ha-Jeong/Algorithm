import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1781 {
    static int n;
    static int[] time;
    static int[] ramen;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        ArrayList<Node> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            list.add(new Node(t,r));
        }

        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Node now : list){
            int size = pq.size();

            if(size < now.time){
                pq.add(now.ramen);
            } else if(size == now.time){
                int peek = pq.peek();

                if(now.ramen > peek){
                    pq.poll();
                    pq.add(now.ramen);
                }
            }
        }

        int ans = 0;
        while(!pq.isEmpty()){
            ans += pq.poll();
        }


        bw.write(ans+"");
        bw.flush();
        bw.close();

    }

    static class Node implements Comparable<Node> {
        int time;
        int ramen;

        Node(int time, int ramen){
            this.time = time;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Node n){
            if(this.time == n.time) return n.ramen - this.ramen;
            return this.time - n.time;
        }
    }
}
