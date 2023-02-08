import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1826 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int ans = 0;
        PriorityQueue<Integer> fuel = new PriorityQueue<>(Collections.reverseOrder());
        while(p < l){
            while(!pq.isEmpty() && pq.peek().location <= p){
                fuel.add(pq.poll().fuel);
            }

            if(fuel.isEmpty()){
                ans = -1;
                break;
            }

            ans++;
            p += fuel.poll();
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }

    static class Node implements Comparable<Node>{
        int location;
        int fuel;

        Node(int location, int fuel){
            this.location = location;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Node n){
            if(this.location == n.location) return n.fuel - this.fuel;
            return this.location - n.location;
        }
    }
}
