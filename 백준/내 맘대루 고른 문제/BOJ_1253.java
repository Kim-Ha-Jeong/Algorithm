import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        HashMap<Long, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
            ArrayList<Integer> tmp = new ArrayList<>();

            if(map.containsKey(arr[i])){
                tmp = map.get(arr[i]);
            }
            tmp.add(i);
            map.put(arr[i], tmp);
        }

        long ans = 0;
        for(int i=0; i<n; i++){
            long sum = arr[i];
            boolean flag = false;
            for(long a : map.keySet()){
                if(sum == a && map.get(a).size() == 1) continue;
                long b = sum - a;
                if(!map.containsKey(b)) continue;
                int size = map.get(b).size();
                if(sum == a && b == sum && size < 3) continue;
                if(sum == b && size == 1) continue;
                if(a == b && size < 2) continue;

                flag = true;
                break;
            }

            if(flag) ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
