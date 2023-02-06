import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14658 {
    static int n,m,l,k;
    static boolean[][] map;

    static ArrayList<Node> stars = new ArrayList<Node>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            stars.add(new Node(x,y));
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                Node star1 = stars.get(i);
                Node star2 = stars.get(j);

                int cnt = 0;
                for(int s=0; s<k; s++){
                    Node target =stars.get(s);

                    if(!falling(star1.x, star2.y, target.x, target.y)){
                        cnt++;
                    }
                }

                ans = Math.min(cnt, ans);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }

    static boolean falling(int boundX, int boundY, int targetX, int targetY){
        if(boundX <= targetX && targetX <= boundX + l && boundY <= targetY && targetY <= boundY+l) return true;
        return false;
    }

    static class Node {
        int x;
        int y;

        Node(int x,int y){
            this.x =x;
            this.y = y;
        }
    }
}
