import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_1935 {
    static int n;
    static char[] op;
    static int[] num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        op = br.readLine().toCharArray();
        num = new int[n];

        for(int i=0; i<n; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();

        for(int i=0; i<op.length; i++){
            char ch = op[i];

            if(ch >= 'A' && ch <= 'Z'){
                double d = num[ch - 'A'];
                stack.add(d);
            } else {
                double a = stack.pop();
                double b = stack.pop();

                switch(ch){
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(b-a);
                        break;
                    case '*':
                        stack.push(a*b);
                        break;
                    case '/':
                        stack.push(b/a);
                        break;
                }
            }
        }

        System.out.printf("%.2f", stack.pop());
    }

}
