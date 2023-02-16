import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_10800 {
    static int n;
    static Ball[] ball;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        ball = new Ball[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            ball[i] = new Ball(i,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] color = new int[200001];
        Arrays.sort(ball);

        int[] cnt = new int[200001];
        int[] sum = new int[n];
        int same = 1;

        ArrayList<Node> ans = new ArrayList<Node>();
        sum[0] = ball[0].width;
        color[ball[0].color] += ball[0].width;
        ans.add(new Node(ball[0].idx, 0));
        for(int i=1; i<n; i++){
            Ball cur = ball[i];

            int c = cur.color;

            if(ball[i-1].width == ball[i].width){
                cnt[ball[i-1].color] += ball[i-1].width;
                int tmp = i - same - 1 >= 0 ? sum[i-same-1] : 0;
                ans.add(new Node(ball[i].idx, tmp - color[c] + cnt[c]));
                same++;
            } else {
                cnt = new int[200001];
                ans.add(new Node(ball[i].idx, sum[i-1]-color[c]));
                same = 1;
            }
            color[c]+=cur.width;
            sum[i] = sum[i-1] + cur.width;
        }

        Collections.sort(ans);

        StringBuffer sb = new StringBuffer();
        for(Node cur : ans){
            sb.append(cur.cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cnt;

        Node(int idx, int cnt){
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n){
            return this.idx - n.idx;
        }
    }
    static class Ball implements Comparable<Ball>{
        int idx;
        int width;
        int color;

        Ball(int idx, int color, int width){
            this.idx = idx;
            this.width = width;
            this.color = color;
        }

        @Override
        public int compareTo(Ball b){
            return this.width - b.width;
        }
    }
}
