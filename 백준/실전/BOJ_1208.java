import java.util.*;
import java.io.*;

public class BOJ_1208 {
    static int n, s;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();
        arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        getPartSum(0, n / 2, 0, left);
        getPartSum(n / 2, n, 0, right);

        Collections.sort(left);
        Collections.sort(right);

        long ans = solve(left, right);

        if (s == 0)
            ans--;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static long solve(ArrayList<Long> left, ArrayList<Long> right) {
        int l = 0;
        int r = right.size() - 1;
        long cnt = 0;

        while (l < left.size() && r >= 0) {
            long a = left.get(l);
            long b = right.get(r);

            long sum = a + b;

            if (sum == s) {
                long cnt1 = 0;
                while (l < left.size() && left.get(l) == a) {
                    l++;
                    cnt1++;
                }

                long cnt2 = 0;
                while (r >= 0 && right.get(r) == b) {
                    r--;
                    cnt2++;
                }

                cnt += cnt1 * cnt2;
            } else if (sum < s) {
                l++;
            } else {
                r--;
            }
        }

        return cnt;
    }

    static void getPartSum(int idx, int end, long sum, ArrayList<Long> list) {
        if (idx == end) {
            list.add(sum);
            return;
        }

        getPartSum(idx + 1, end, sum + arr[idx], list);
        getPartSum(idx + 1, end, sum, list);
    }
}
