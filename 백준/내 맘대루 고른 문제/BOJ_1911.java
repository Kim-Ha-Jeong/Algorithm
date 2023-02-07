import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1911 {
    static int n,l;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int ans = 0;
        ArrayList<Node> peddle = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            peddle.add(new Node(start,end));
        }

        Collections.sort(peddle);

        int hidden = 0;
        for(Node now : peddle){
            if(hidden > now.end) continue;
            int tmp = 0;
            if(hidden > now.start){
                tmp = now.end - hidden;
            } else {
                tmp = now.end - now.start;
            }
            int cnt = (int)Math.ceil(tmp / (double)l);
            hidden = hidden > now.start ? hidden + cnt * l : now.start + cnt * l;
            ans += cnt;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        int start;
        int end;

        Node(int start, int end){
            this.start = start;
            this.end  = end;
        }

        @Override
        public int compareTo(Node n){
            if(this.start == n.start) return n.end - this.end;
            return this.start - n.start;
        }
    }
}
