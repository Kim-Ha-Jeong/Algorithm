import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2258 {
    static int n;
    static long m;
    static Bread[] bread;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        bread = new Bread[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bread[i] = new Bread(weight, cost);
        }

        Arrays.sort(bread);

        int ans = Integer.MAX_VALUE;
        int price = -1;
        int weight = 0;
        boolean flag = false;

        for(int i=0; i<n; i++){
            weight += bread[i].weight;

            if(i > 0 && bread[i-1].cost == bread[i].cost){
                price += bread[i].cost;
            } else {
                price = bread[i].cost;
            }

            if(weight >= m){
                flag = true;
                ans = Math.min(ans, price);
            }
        }

        bw.write(flag ? ans+"" : "-1");
        bw.flush();
        bw.close();
    }

    static class Bread implements Comparable<Bread> {
        int weight;
        int cost;

        Bread(int weight, int cost){
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bread b){
            if(this.cost == b.cost) return b.weight - this.weight;
            return this.cost - b.cost;
        }
    }
}
