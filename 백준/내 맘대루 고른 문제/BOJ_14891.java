import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14891 {
    static char[][] gear;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        gear = new char[4][8];
        for(int i=0; i<4; i++){
            gear[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());

        while(n-->0){
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());

            if(idx == 0){
                int now = 0, cnt = 1;
                while(now+1 < 4){
                    if(gear[now][2] == gear[now+1][6]) break;
                    cnt++;
                    now++;
                }

                for(int i=0; i<cnt; i++){
                    rotate(idx+i, d);
                    d*=(-1);
                }
            } else if(idx == 3){
                int now = 3, cnt = 1;
                while(now-1>=0){
                    if(gear[now][6] == gear[now-1][2]) break;
                    cnt++;
                    now--;
                }

                for(int i=0; i<cnt; i++){
                    rotate(idx-i, d);
                    d*=(-1);
                }
            } else {
                int left = 1, now = idx, right = 1;
                while(now-1>=0){
                    if(gear[now][6] == gear[now-1][2]) break;
                    left++;
                    now--;
                }

                now = idx;
                while(now+1 < 4){
                    if(gear[now][2] == gear[now+1][6]) break;
                    right++;
                    now++;
                }

                int nd = d;
                for(int i=0; i<left; i++){
                    rotate(idx-i, nd);
                    nd *= (-1);
                }

                nd = (-1)*d;
                for(int i=1; i<right; i++){
                    rotate(idx+i, nd);
                    nd *= (-1);
                }
            }
        }

        int ans = 0;
        int num = 1;
        for(int i=0; i<4; i++){
            if(gear[i][0] == '1') ans += num;
            num *= 2;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void rotate(int idx, int d){
        if(d == 1) {
            char tmp = gear[idx][0];
            for (int i = 7; i > 0; i--) {
                gear[idx][(i + 1) % 8] = gear[idx][i];
            }
            gear[idx][1] = tmp;
        } else {
            char tmp = gear[idx][0];
            for(int i=1; i<8; i++){
                gear[idx][i-1] = gear[idx][i];
            }
            gear[idx][7] = tmp;
        }
    }
}
