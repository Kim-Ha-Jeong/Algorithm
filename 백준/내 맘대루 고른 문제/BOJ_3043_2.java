import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_3043_2 {
    static int n;
    static ArrayList<Tank> tank;
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

            tank.add(new Tank(i+1, x,y));
        }

        StringBuffer sb = new StringBuffer();
        Collections.sort(tank, new Comparator<Tank>(){
            @Override
            public int compare(Tank t1, Tank t2){
                return t1.x - t2.x;
            }
        });

        int ans = 0;
        for(int i=0; i<n; i++){
            Tank now = tank.get(i);
            int target = i+1;
            if(target < now.x){
                String str = now.idx+" U\n";
                sb.append(str.repeat(now.x - target));
                ans += (now.x - target);
            }
        }

        for(int i=n-1; i>=0; i--){
            Tank now = tank.get(i);
            int target = i+1;
            if(target > now.x){
                String str = now.idx+" D\n";
                sb.append(str.repeat(target - now.x));
                ans += (target - now.x);
            }
        }

        Collections.sort(tank, new Comparator<Tank>(){
            @Override
            public int compare(Tank t1, Tank t2){
                return t1.y - t2.y;
            }
        });

        for(int i=0; i<n; i++){
            Tank now = tank.get(i);
            int target = i+1;
            if(target < now.y){
                String str = now.idx+" L\n";
                sb.append(str.repeat(now.y - target));
                ans += (now.y - target);
            }
        }

        for(int i=n-1; i>=0; i--){
            Tank now = tank.get(i);
            int target = i+1;
            if(target > now.y){
                String str = now.idx+" R\n";
                sb.append(str.repeat(target - now.y));
                ans += (target - now.y);
            }
        }

        String str = ans+"\n";
        sb.insert(0, str);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Tank{
        int idx;
        int x;
        int y;

        Tank(int idx, int x, int y){
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
}
