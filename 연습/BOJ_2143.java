import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2143 {
    static ArrayList<Long> a = new ArrayList<>();
    static ArrayList<Long> b = new ArrayList<>();
    static long t;
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        t = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] B = new int[m];

        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                a.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                b.add(sum);
            }
        }

        Collections.sort(a);
        Collections.sort(b);

        solve();
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        int low = 0;
        int high = b.size() - 1;

        while (low < a.size() && high >= 0) {

            long A = a.get(low);
            long B = b.get(high);
            long sum = a.get(low) + b.get(high);

            if (sum == t) {
                long lenA = 0;
                long lenB = 0;
                while (low < a.size() && a.get(low) == A) {
                    low++;
                    lenA++;
                }

                while (high >= 0 && b.get(high) == B) {
                    high--;
                    lenB++;
                }

                ans += lenA * lenB;
            } else if (sum < t) {
                low++;
            } else {
                high--;
            }
        }
    }

}
