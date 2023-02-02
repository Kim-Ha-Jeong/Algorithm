import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

public class BOJ_1918 {
    static char[] op;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        op = br.readLine().toCharArray();

        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();

        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('(', 0);
        priority.put(')', 0);

        for(int i=0; i<op.length; i++){
            char now = op[i];

            switch(now) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(now)){
                        sb.append(stack.pop());
                    }
                    stack.add(now);
                    break;
                case '(':
                    stack.add(now);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(now);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
