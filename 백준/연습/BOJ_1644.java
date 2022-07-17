import java.util.*;
import java.io.*;

public class BOJ_1644 {
    static boolean[] prime;
    static int n;
    static ArrayList<Integer> pri = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        isPrime();

        for (int i = 2; i < n + 1; i++) {
            if (!prime[i])
                continue;
            pri.add(i);
        }

        pri.add(0);

        int size = pri.size();
        int back = 0, front = 0;
        int sum = pri.get(0);

        int cnt = 0;
        while (true) {
            if (back >= size - 1)
                break;
            if (sum >= n) {
                if (sum == n)
                    cnt++;
                sum -= pri.get(front++);
            } else {
                sum += pri.get(++back);
            }
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();

    }

    static void isPrime() {
        for (int i = 2; i * i < n + 1; i++) {
            for (int j = i * i; j < n + 1; j += i) {
                prime[j] = false;
            }
        }
    }

}
