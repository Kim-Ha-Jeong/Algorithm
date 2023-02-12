import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_20922 {
    static int n,k;
    static HashMap<Integer,Integer> map = new HashMap<>();
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        map.put(arr[0],1);

        int ans = 0;
        int front = 0, back = 1;
        boolean flag = false;
        while(front < n+1 && back < n+1){
            if(!flag && back < n){
                if(map.containsKey(arr[back])){
                    if(map.get(arr[back]) == k){
                        ans = Math.max(back - front, ans);
                        flag = true;
                    }
                    map.put(arr[back], map.get(arr[back])+1);
                } else {
                    map.put(arr[back], 1);
                }
                back++;
            } else if(flag && front < n){
                if(map.containsKey(arr[front])){
                    if(map.get(arr[front]) == k+1){
                        flag = false;
                    }
                    if(map.get(arr[front])-1 > 0){
                        map.put(arr[front], map.get(arr[front])-1);
                    } else {
                        map.remove(arr[front]);
                    }
                }
                front++;
            } else if(back == n) break;
        }

        ans = Math.max(back-front, ans);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
