import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_22251 {
    static int n,k,p,x;
    static int[][] change = {{0,4,3,3,4,3,2,3,1,2},{4,0,5,3,2,5,6,1,5,4},{3,5,0,2,5,4,3,4,2,3},{3,3,2,0,3,2,3,2,2,1},{4,2,5,3,0,3,4,3,3,2},{3,5,4,2,3,0,1,4,2,1},{2,6,3,3,4,1,0,5,1,2},{3,1,4,2,3,4,5,0,4,3},{1,5,2,2,3,2,1,4,0,1},{2,4,3,1,2,1,2,3,1,0}};

    static char[] floor;
    static char[] num;
    static int[] bound;
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        floor = new char[k];

        int len = floor.length;
        String str = String.valueOf(x);
        String tmp = String.valueOf(n);

        bound = new int[tmp.length()];
        for(int i=0; i<tmp.length(); i++){
            bound[i] = Integer.parseInt(tmp.substring(0,i+1));
        }

        num = ("0".repeat(len - str.length())+str).toCharArray();
        backtracking(0, 0, "");

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void backtracking(int depth, int cnt, String number){
        if(depth == k){
            if(cnt <= p){
                int tmp = Integer.parseInt(number);
                if(tmp != 0 && tmp != x){
                    ans++;
                }
            }
            return;
        }

        for(int i=0; i<10; i++){
            if(cnt + change[num[depth] - '0'][i] > p) continue;
            if(bound[depth] < Integer.parseInt(number+i)) continue;
            backtracking(depth+1,cnt + change[num[depth] - '0'][i], number+i);
        }
    }


}
