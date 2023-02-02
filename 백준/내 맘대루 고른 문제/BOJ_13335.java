import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13335 {

    static int n, w, l;
    static Truck arr[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        arr = new Truck[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = new Truck(Integer.parseInt(st.nextToken()), -1);
        }

        int idx = 0;
        int sec = 0;
        int bridge = 0;

        PriorityQueue<Truck> pq = new PriorityQueue<>();
        while(idx < n){
            while(!pq.isEmpty()){
                if(pq.peek().sec <= sec - w){
                    bridge -= pq.poll().weight;
                } else {
                    break;
                }
            }

            if(pq.size() == 0 || bridge + arr[idx].weight <= l){
                arr[idx].sec = sec;
                bridge += arr[idx].weight;
                pq.add(arr[idx]);
                idx++;
            }

            sec++;
        }

        sec += w;

        bw.write(sec+"");
        bw.flush();
        bw.close();
    }

    static class Truck implements Comparable<Truck> {
        int weight;
        int sec;

        Truck(int weight, int sec){
            this.weight = weight;
            this.sec = sec;
        }

        @Override
        public int compareTo(Truck t){
            return this.sec - t.sec;
        }
    }
}
