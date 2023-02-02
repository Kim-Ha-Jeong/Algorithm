import java.util.*;
import java.io.*;

public class BOJ_2143 {
    static long t, cnt = 0;
    static int n, m;
    static int[] a, b;
    static ArrayList<Long> partA, partB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        t = Long.parseLong(br.readLine());

        n = Integer.parseInt(br.readLine());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        partA = new ArrayList<>();
        partB = new ArrayList<>();

        getPartsum(n, a, partA);
        getPartsum(m, b, partB);

        Collections.sort(partA);
        Collections.sort(partB);
        cal();

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }

    static void cal() {
        int aLen = partA.size();
        int bLen = partB.size();

        int aIdx = 0, bIdx = bLen - 1;

        while (aIdx < aLen && bIdx >= 0) {
            long numA = partA.get(aIdx);
            long numB = partB.get(bIdx);

            long sum = numA + numB;

            if (sum == t) {
                long cnt1 = 0;
                while (aIdx < aLen && partA.get(aIdx) == numA) {
                    cnt1++;
                    aIdx++;
                }

                long cnt2 = 0;
                while (bIdx >= 0 && partB.get(bIdx) == numB) {
                    cnt2++;
                    bIdx--;
                }

                cnt += cnt1 * cnt2;
            } else if (sum < t) {
                aIdx++;
            } else {
                bIdx--;
            }
        }

    }

    static void getPartsum(int end, int[] arr, ArrayList<Long> result) {
        for (int i = 0; i < end; i++) {
            long sum = 0;
            for (int j = i; j < end; j++) {
                sum += arr[j];
                result.add(sum);
            }
        }
    }
}