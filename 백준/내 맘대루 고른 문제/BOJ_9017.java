import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_9017 {
    static int[] team = new int[201];
    static int[] arr;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[n];

            int[] all = new int[201];
            for(int i=0; i<n; i++){
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                all[num]++;
            }

            HashSet<Integer> team = new HashSet<>();
            for(int i=1; i<201; i++){
                if(all[i] == 6){
                    team.add(i);
                }
            }

            HashMap<Integer,ArrayList<Integer>> score = new HashMap<>();

            int idx = 1;
            for(int i=0; i<n; i++){
                if(!team.contains(arr[i])) continue;
                ArrayList<Integer> list = new ArrayList<>();
                if(score.containsKey(arr[i])){
                    list = score.get(arr[i]);
                }
                list.add(idx);
                score.put(arr[i], list);
                idx++;
            }

            int ans = -1, five = -1, max = Integer.MAX_VALUE;
            for(int key : score.keySet()){
                ArrayList<Integer> tmp = score.get(key);

                int sum = 0;
                for(int i=0; i<4; i++){
                    sum += tmp.get(i);
                }

                if(sum < max){
                    max = sum;
                    five = tmp.get(4);
                    ans = key;
                } else if(sum == max){
                    if(five > tmp.get(4)){
                        five = tmp.get(4);
                        ans = key;
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
