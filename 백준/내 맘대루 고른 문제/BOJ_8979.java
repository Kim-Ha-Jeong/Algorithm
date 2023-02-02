import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_8979 {
    static int n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        ArrayList<Medal> medal = new ArrayList<>();
        
        Medal K = new Medal(0,0,0,0);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d= Integer.parseInt(st.nextToken());
            
            Medal m = new Medal(a,b,c,d);
            if(a == k){
                m.flag = -1;
                K = m;
            }
            medal.add(m);
        }
        
        Collections.sort(medal);
        
        int grade = medal.indexOf(K)+1;

        bw.write(grade+"");
        bw.flush();
        bw.close();
    }

    static class Medal implements Comparable<Medal>{
        int idx;
        int flag = 0;
        int gold;
        int silver;
        int bronze;
        
        Medal(int idx, int gold, int silver, int bronze){
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
        
        @Override
        public int compareTo(Medal m){
            if(this.gold == m.gold){
                if(this.silver == m.silver){
                    if(this.bronze == m.bronze){
                        return this.flag - m.flag;
                    }
                    return m.bronze - this.bronze;
                }
                return m.silver - this.silver;
            }
            return m.gold - this.gold;
        }
    }
}
