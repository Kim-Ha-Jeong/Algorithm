import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2141 {
    static int n;
    static long ans = -1;

    static Node[] node;
    static long result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        node = new Node[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            node[i] = new Node(x,a);
            result += a;
        }

        Arrays.sort(node);

        long sum = 0;
        for(int i=0; i<n; i++){
            sum += node[i].a;
            if(sum >= (result+1)/2){
                ans = node[i].x;
                break;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        long x;
        long a;

        Node(long x, long a){
            this.x = x;
            this.a = a;
        }

        @Override
        public int compareTo(Node n){
            if(this.x == n.x){
                return (int)(this.a - n.a);
            }
            return (int)(this.x - n.x);
        }
    }
}
