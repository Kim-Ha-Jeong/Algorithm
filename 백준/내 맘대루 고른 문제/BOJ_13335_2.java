import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_2 {

    static int n, w, l;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        Queue<Integer> truck = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            truck.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>();
        for(int i=0; i<w; i++){
            bridge.add(0);
        }

        int sec = 0;
        int weight = 0;
        while(!bridge.isEmpty()){
            weight -= bridge.poll();

            if(!truck.isEmpty()){
                int now = truck.peek();
                if(weight + now <= l){
                    truck.poll();
                    weight += now;
                    bridge.add(now);
                } else {
                    bridge.add(0);
                }
            }

            sec++;
        }

        bw.write(sec+"");
        bw.flush();
        bw.close();
    }
}
