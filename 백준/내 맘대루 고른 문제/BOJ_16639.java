import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_16639 {
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        char[] c = br.readLine().toCharArray();

        ArrayList<Integer> num = new ArrayList<>();
        ArrayList<Character> op = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(i % 2 == 0){
                num.add(c[i] - '0');
            } else {
                op.add(c[i]);
            }
        }

        dfs(num, op);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dfs(ArrayList<Integer> num, ArrayList<Character> op){
        if(op.size() == 0){
            ans = Math.max(ans, num.remove(0));
            return;
        }

        for(int i=0; i<num.size()-1; i++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int n : num){
                tmp.add(n);
            }
            ArrayList<Character> opTmp = new ArrayList<>();
            for(Character ch : op){
                opTmp.add(ch);
            }
            int a = tmp.remove(i);
            int b = tmp.remove(i);

            char o = opTmp.remove(i);

            switch(o){
                case '+':
                    tmp.add(i, a+b);
                    break;
                case '-':
                    tmp.add(i, a-b);
                    break;
                case '*':
                    tmp.add(i, a*b);
                    break;
            }

            dfs(tmp, opTmp);

        }
    }
}
