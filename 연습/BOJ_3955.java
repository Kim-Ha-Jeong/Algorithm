import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3955 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());

        // A(-x) + By = 1의 형태로 변환

        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            // 1. 해 검증
            // D = gcd(a, b)
            // Ax + By = C 에서 C % D == 0 이어야 해가 존재함
            Result gcd = extendedGcd(A, B);
            long D = gcd.r;
            if (gcd.r != 1) {
                sb.append("IMPOSSIBLE").append("\n");
                continue;
            }

            // 2. 초기해 구하기
            long x0 = gcd.s; // C랑 D가 1이라서 생략
            long y0 = gcd.t;
            // x0 = s*C/D;
            // y0 = t*C/D;

            // 3. 일반해 구하기
            // x = x0 + B / D * k
            // y = y0 - A /D * k

            // 4. k의 범위
            // x < 0
            // x0 + B * k < 0
            // k < -x0 / B

            // 0 < y <= 1e9
            // 0 < y0 - A * k <= 1e9
            // -y0 < -A * k <= 1e9 - y0
            // (y0 - 1e9)/A <= k < y0/A

            // (y0 - 1e9)/A <= k < y0/A
            // k < -x0 / B
            long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
            long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
            long k = Math.min(kFromX, kFromY);

            long kLimitFromY = (long) Math.ceil((double) (y0 - 1e9) / (double) A);

            if (kLimitFromY <= k) {
                long y = y0 - A / D * k;
                sb.append(y);
            } else {
                sb.append("IMPOSSIBLE");
            }

            sb.append("\n");
            // 5. 만족하는 k가 있는 지 찾고 k를 통해 y를 구함
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static Result extendedGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while (r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1; // 밑으로 내림
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }

        return new Result(s0, t0, r0);
    }

    static class Result {
        long s;
        long t;
        long r;

        public Result(long s, long t, long r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }
    }
}
