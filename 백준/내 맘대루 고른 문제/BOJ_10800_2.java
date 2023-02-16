import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10800_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Ball[] balls = new Ball[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i,c,s);
        }

        int[] result = new int[n];
        int[] color = new int[n+1];
        int idx = 0;
        int sum = 0;

        Arrays.sort(balls);
        for(int i=0; i<n; i++){
            Ball cur = balls[i];

            while(balls[idx].size < cur.size){
                sum += balls[idx].size;
                color[balls[idx].color] += balls[idx].size;
                idx++;
            }

            result[cur.idx] = sum - color[cur.color];
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<n; i++){
            sb.append(result[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Ball implements Comparable<Ball>{
        int idx;
        int color;
        int size;

        Ball(int idx, int color, int size){
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball b){
            return this.size - b.size;
        }
    }
}
