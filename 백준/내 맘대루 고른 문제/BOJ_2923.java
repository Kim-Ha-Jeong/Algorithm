import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2923 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[101];
        int[] B = new int[101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())]++;
            B[Integer.parseInt(st.nextToken())]++;
            int[] tmpA = new int[101];
            int[] tmpB = new int[101];

            System.arraycopy(A, 0, tmpA, 0, A.length);
            System.arraycopy(B, 0, tmpB, 0, B.length);

            int result = 0;
            int aIdx = 100, bIdx = 1;
            while (aIdx >= 1 && bIdx < 101) {
                while (aIdx >= 1 && tmpA[aIdx] == 0) aIdx--;
                while (bIdx < 101 && tmpB[bIdx] == 0) bIdx++;

                if (aIdx == 0 || bIdx == 101) break;

                result = Math.max(result, aIdx + bIdx);

                if (tmpA[aIdx] > tmpB[bIdx]) {
                    tmpA[aIdx] -= tmpB[bIdx];
                    tmpB[bIdx] = 0;
                } else {
                    tmpB[bIdx] -= tmpA[aIdx];
                    tmpA[aIdx] = 0;
                }
            }

            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
