import java.io.*;

public class BOJ_4375 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = null;
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int idx = 1;
            int sol = 1;
            while (true) {
                if (sol % n == 0)
                    break;
                sol = (sol * 10 + 1) % n;
                idx++;
            }

            System.out.println(idx);
        }
    }

}
