import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17420 {
    static int n;
    static Gift[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        long[] a = new long[n];
        g = new Gift[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            a[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
           g[i] = new Gift(a[i], Integer.parseInt(st.nextToken()));
        }

        long ans = 0;
        Arrays.sort(g);

        long max = g[0].use;
        long cur = -1;

        for(int i=0; i<n; i++){
            if(max >= g[i].rest){
                if(max < g[i].use){
                    max = g[i].use;
                }

                long cnt = (max - g[i].rest + 29)/30;
                g[i].rest += (cnt * 30);
                ans += cnt;
            }
            cur = Math.max(cur, g[i].rest);

            if((i+1) < n && g[i].use != g[i+1].use){
                max = cur;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Gift implements Comparable<Gift> {
        long rest;
        long use;

        Gift(long rest, long use){
            this.rest = rest;
            this.use = use;
        }

        @Override
        public int compareTo(Gift g){
            if(this.use == g.use) return (int)(this.rest - g.rest);
            return (int)(this.use - g.use);
        }
    }
}
