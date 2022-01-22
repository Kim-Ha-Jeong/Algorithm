import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ_3425 {
    static String err = "ERROR";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        ArrayList<String> list = new ArrayList<>();
        boolean flag = false;

        while (true) {
            String str = br.readLine();

            if (str.equals("QUIT")) {
                break;
            } else if (str.equals("END")) {
                flag = true;
            } else if (str.equals("")) {
                list = new ArrayList<>();
                flag = false;
                continue;
            }

            if (!flag) {
                list.add(str);
            } else {
                int n = Integer.parseInt(br.readLine());
                for (int i = 0; i < n; i++) {
                    int num = Integer.parseInt(br.readLine());
                    sb.append(solve(num, list)).append("\n");
                }

                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String solve(int num, ArrayList<String> list) {
        Stack<Integer> stack = new Stack<>();
        stack.add(num);

        for (String s : list) {
            if (s.contains("NUM")) {
                int n = Integer.parseInt(s.split(" ")[1]);
                stack.add(n);
            } else if (stack.size() == 0) {
                return err;
            } else if (s.equals("DUP")) {
                stack.add(stack.peek());
            } else if (s.equals("POP")) {
                stack.pop();
            } else if (s.equals("INV")) {
                stack.add((-1) * stack.pop());
            } else if (stack.size() == 1) {
                return err;
            } else {
                int a = stack.pop();
                int b = stack.pop();

                if (s.equals("SWP")) {
                    stack.add(a);
                    stack.add(b);
                } else if (s.equals("ADD")) {
                    long tmp = (long) a + (long) b;
                    if (Math.abs(tmp) > 1e9)
                        return err;
                    stack.add(a + b);
                } else if (s.equals("SUB")) {
                    long tmp = (long) b - (long) a;
                    if (Math.abs(tmp) > 1e9)
                        return err;
                    stack.add(b - a);
                } else if (s.equals("MUL")) {
                    long tmp = (long) a * (long) b;
                    if (Math.abs(tmp) > 1e9)
                        return err;
                    stack.add(a * b);
                } else if (a == 0) {
                    return err;
                } else {
                    int tmpA = Math.abs(a);
                    int tmpB = Math.abs(b);

                    if (s.equals("DIV")) {
                        int res = tmpB / tmpA;
                        if ((a > 0 && b < 0) || (a < 0 && b > 0))
                            res = (-1) * res;
                        stack.add(res);
                    } else if (s.equals("MOD")) {
                        int ret = tmpB % tmpA;
                        if (b < 0)
                            ret = (-1) * ret;
                        stack.add(ret);
                    }
                }
            }
        }

        if (stack.size() == 1) {
            return Integer.toString(stack.pop());
        } else {
            return err;
        }
    }

}
