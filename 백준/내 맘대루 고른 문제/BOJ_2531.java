import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_2531 {
    static int n,d,k,c;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<k; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            } else {
                map.put(arr[i], 1);
            }
        }

        int ans = map.containsKey(c) ? map.size() : map.size()+1;

        if(n != k) {
            boolean flag = true;
            int start = 0;
            int end = k;

            while (start != n) {
                if (map.get(arr[start]) - 1 == 0) map.remove(arr[start]);
                else map.put(arr[start], map.get(arr[start]) - 1);

                if (map.containsKey(arr[end])) {
                    map.put(arr[end], map.get(arr[end]) + 1);
                } else {
                    map.put(arr[end], 1);
                }

                int size = map.size();
                if (!map.containsKey(c)) size++;

                ans = Math.max(ans, size);
                start++;
                end = (end + 1) % n;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
