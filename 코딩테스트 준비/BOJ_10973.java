import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_10973 {
    static int n;
    static int[] arr;
    static int[] copyArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        copyArr = new int[n];

        String[] s = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
            copyArr[i] = arr[i];
        }

        Arrays.sort(copyArr);

        if (isRight(copyArr)) {
            System.out.println(-1);
            return;
        }

        int[] temp;
        if (arr[n - 2] > arr[n - 1]) { // 뒤에 2자리가 내림차순이라면 swap
            int tmp = arr[n - 1];
            arr[n - 1] = arr[n - 2];
            arr[n - 2] = tmp;

            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
        } else { // 뒤에 2자리가 오름차순이라면
            int idx = -1;
            for (int i = n - 1; i >= 1; i--) {
                if (arr[i - 1] > arr[i]) { // 오름차순이 깨지는 부분을 찾음 : i - 1
                    idx = i - 1;
                    break;
                }
            }

            if (idx != -1) {
                int max = -1;
                int index = -1;
                for (int i = idx + 1; i < n; i++) {
                    if (max < arr[i] && arr[idx] > arr[i]) { // idx보다 작은 것 중 가장 큰 수 찾음
                        max = Math.max(arr[i], max);
                        index = i;
                    }
                }

                int tmp = arr[index];
                arr[index] = arr[idx];
                arr[idx] = tmp; // 2개 교환

                int len = n - idx - 1;

                temp = new int[len];
                int first = 0;
                for (int j = idx + 1; j < n; j++) {
                    temp[first++] = arr[j];
                }

                Arrays.sort(temp); // 뒤에 있는 요소들 내림차순

                for (int j = 0; j <= idx; j++) {
                    sb.append(arr[j]).append(" ");
                }

                for (int j = len - 1; j >= 0; j--) {
                    sb.append(temp[j]).append(" ");
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static boolean isRight(int[] order) {
        for (int i = 0; i < n; i++) {
            if (order[i] != arr[i])
                return false;
        }

        return true;
    }
}
