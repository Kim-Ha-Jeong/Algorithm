import java.util.*;
import java.io.*;

public class BOJ_10845 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int k = Integer.parseInt(br.readLine());

        LinkedList<Integer> q = new LinkedList<>();

        while (k-- > 0) {
            String str = br.readLine();

            if (str.equals("pop") || str.equals("front") || str.equals("back")) {
                if (q.isEmpty())
                    sb.append(-1).append("\n");
                else if (str.equals("back")) {
                    sb.append(q.get(q.size() - 1)).append("\n");
                } else if (str.equals("pop")) {
                    sb.append(q.poll()).append("\n");
                } else {
                    sb.append(q.peek()).append("\n");
                }
            } else if (str.equals("empty")) {
                if (q.isEmpty())
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            } else if (str.equals("size")) {
                sb.append(q.size()).append("\n");
            } else {
                int x = Integer.parseInt(str.split(" ")[1]);
                q.add(x);
            }

        }

        sb.deleteCharAt(sb.length() - 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
