import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_16639_2 {

    static int ans = Integer.MIN_VALUE;
    static ArrayList<Integer> num = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        char[] c = br.readLine().toCharArray();


        for(int i=0; i<n; i++){
            if(i % 2 == 0){
                num.add(c[i] - '0');
            } else {
                op.add(c[i]);
            }
        }

        dfs(0, num.get(0));

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dfs(int now, int sum){
        if(now >= op.size()){
            ans = Math.max(ans, sum);
            return;
        }

        int tmp = cal(now, sum, num.get(now+1));
        dfs(now+1, tmp);

        if(now+1 < op.size()){
            int tmp2 = cal(now+1, num.get(now+1), num.get(now+2));
            int ret = cal(now, sum, tmp2);
            dfs(now+2, ret);
        }
    }

    static int cal(int idx, int a, int b){
        switch(op.get(idx)){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
        }
        return 1;
    }
}
