import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_8980 {
    static int n,c;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n+1];

        int m = Integer.parseInt(br.readLine());
        ArrayList<Node> list = new ArrayList<Node>();
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            list.add(new Node(start,end,box));
        }

        Collections.sort(list);

        int ans = 0;
        for(Node now : list){
            int start = now.start;
            int end = now.end;
            int box = now.box;

            int max = 0;
            for(int i=start; i<end; i++){
                max = Math.max(max, arr[i]);
            }

            int possible = Math.min(c-max, box);
            ans += possible;
            for(int i=start; i<end; i++){
                arr[i] += possible;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }

    static class Node implements Comparable<Node>{
        int start;
        int end;
        int box;

        Node(int start, int end, int box){
            this.start = start;
            this.end = end;
            this.box = box;
        }

        @Override
        public int compareTo(Node n){
            if(this.end == n.end) return this.start - n.start;
            return this.end - n.end;
        }
    }
}
