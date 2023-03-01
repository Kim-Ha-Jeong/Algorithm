import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2785 {
    static int n;
    static ArrayList<Integer> chain;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        chain = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            chain.add(num);
        }

        Collections.sort(chain);
        int ans = 0;

        while(true){
            if(chain.size() < 2) break;
            chain.set(0, chain.get(0)-1);
            chain.remove(chain.size()-1);
            if(chain.get(0) == 0) chain.remove(0);
            ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }

    static class Node implements Comparable<Node> {
        int idx;
        int len;

        Node(int idx, int len){
            this.idx = idx;
            this.len = len;
        }

        @Override
        public int compareTo(Node n){
            return this.len - n.len;
        }
    }
}
