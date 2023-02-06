import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_20920 {
    static int n,m;
    static HashMap<String,Node> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        while(n-->0){
            String word = br.readLine();

            if(word.length() < m) continue;

            if(map.containsKey(word)){
                map.put(word, new Node(map.get(word).cnt+1, word));
            } else {
                map.put(word, new Node(1, word));
            }
        }

        StringBuffer sb = new StringBuffer();
        List<Map.Entry<String,Node>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        for(Map.Entry<String,Node> entry : entryList){
            sb.append(entry.getKey()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        int cnt;
        String str;

        Node(int cnt, String str){
            this.cnt = cnt;
            this.str = str;
        }

        @Override
        public int compareTo(Node n){
            if(n.cnt == this.cnt) {
                if(this.str.length() == n.str.length()){
                    return this.str.compareTo(n.str);
                }
                return n.str.length() - this.str.length();
            }
            return n.cnt - this.cnt;
        }

    }
}
