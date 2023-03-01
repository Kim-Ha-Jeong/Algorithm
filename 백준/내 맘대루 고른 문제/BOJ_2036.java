import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_2036 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Long> plus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> minus = new PriorityQueue<>();

        long ans = 0;
        int zero = 0;
        for(int i=0; i<n; i++){
            long num = Long.parseLong(br.readLine());
            if(num > 0){
                if(num == 1) {
                    ans+=1;
                    continue;
                }
                plus.add(num);
            } else if(num < 0){
                minus.add(num);
            } else {
                zero++;
            }
        }

        while(!plus.isEmpty()){
            if(plus.size() >= 2){
                long a = plus.poll();
                long b = plus.poll();

                ans += a*b;
            } else {
                long a = plus.poll();

                ans += a;
            }
        }

        while(!minus.isEmpty()){
            if(minus.size() >= 2){
                long a = minus.poll();
                long b = minus.poll();

                ans += a*b;
            } else {
                long a = minus.poll();
                if(zero > 0) a = 0;

                ans += a;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
