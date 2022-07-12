import java.util.*;
import java.io.*;

public class BOJ_10866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        Deque<Integer> dq = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String str = br.readLine();

            if (str.equals("size")) {
                sb.append(dq.size()).append("\n");
            } else if (str.equals("empty")) {
                if (dq.isEmpty())
                    sb.append(1);
                else
                    sb.append(0);
                sb.append("\n");
            } else if (str.contains("push")) {
                int x = Integer.parseInt(str.split(" ")[1]);

                if (str.contains("front")) {
                    dq.addFirst(x);
                } else {
                    dq.addLast(x);
                }
            } else {
                if (dq.isEmpty())
                    sb.append(-1);
                else {
                    if (str.equals("front")) {
                        sb.append(dq.getFirst());
                    } else if (str.equals("back")) {
                        sb.append(dq.getLast());
                    } else if (str.contains("front")) {
                        sb.append(dq.pollFirst());
                    } else {
                        sb.append(dq.pollLast());
                    }
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
