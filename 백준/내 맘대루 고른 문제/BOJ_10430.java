import java.io.*;
import java.util.*;

public class BOJ_10430 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        sb.append((a + b) % c).append("\n");
        sb.append(((a % c) + (b % c)) % c).append("\n");
        sb.append((a * b) % c).append("\n");
        sb.append(((a % c) * (b % c)) % c);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
