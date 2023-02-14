import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_10165 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();

        TreeSet<Integer> ans = new TreeSet<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(start < end){
                pq.add(new Node(i+1,start,end));
                pq.add(new Node(i+1,start+n,end+n));
            } else {
                pq.add(new Node(i+1,start,end+n));
            }

            ans.add(i+1);
        }

        int end = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.end > end){
                end = now.end;
            } else {
                if(ans.contains(now.id)){
                    ans.remove(now.id);
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        for(int i : ans){
            sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        int id;
        int start;
        int end;

        Node(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node n){
            if(this.start == n.start) return n.end - this.end;
            return this.start - n.start;
        }
    }
}
