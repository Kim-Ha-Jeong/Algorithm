import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_4375 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = null;
        while ((s = br.readLine()) != null) {
            int n = Integer.parseInt(s);
            int number = 1;
            int len = 1;

            while (true) {
                if (number % n == 0)
                    break;
                number = (number * 10 + 1) % n;
                len++;
            }

            System.out.println(len);
        }
    }
}
