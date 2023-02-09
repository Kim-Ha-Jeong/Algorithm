import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_3043 {
    static int n;
    static ArrayList<Node> tank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        tank = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tank.add(new Node(i+1, x,y));
        }


        StringBuffer sb = new StringBuffer();
        Collections.sort(tank, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x - o2.x;
            }
        });

        int cnt = 0;
        for(int i=0; i<n; i++){
            Node cur = tank.get(i);
            int x = cur.x;
            int target = i+1;
            while(x > target){
                cnt++;
                sb.append(cur.idx).append(" U\n");
                x--;
            }
            cur.x = x;
        }

        for(int i=n-1; i>=0; i--){
            Node cur = tank.get(i);
            int x = cur.x;
            int target = i+1;
            while(x < target){
                cnt++;
                sb.append(cur.idx).append(" D\n");
                x++;
            }
            cur.x = x;
        }

        Collections.sort(tank, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return o1.y - o2.y;
            }
        });

        for(int i=0; i<n; i++){
            Node cur = tank.get(i);
            int y = cur.y;
            int target = i+1;
            while(y > target){
                cnt++;
                sb.append(cur.idx).append(" L\n");
                y--;
            }
        }

        for(int i=n-1; i>=0; i--){
            Node cur = tank.get(i);
            int y = cur.y;
            int target = i+1;
            while(y < target){
                cnt++;
                sb.append(cur.idx).append(" R\n");
                y++;
            }
        }

        String str = cnt+"\n";
        sb.insert(0, str);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node{
        int idx;
        int x;
        int y;

        Node(int idx, int x, int y){
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
}
