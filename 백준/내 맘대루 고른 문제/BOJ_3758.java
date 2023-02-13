import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_3758 {
    static int n,k,t,m;
    static HashMap<String, Integer> score;
    static Team[] team;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            score = new HashMap<>();
            team = new Team[n+1];
            for(int i=0; i<n+1; i++){
                team[i] = new Team(i,0,0,0);
            }

            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());

                int id = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                team[id].lastIdx = i;
                team[id].submit++;

                String str = id+" "+q;
                if(score.containsKey(str)){
                    int prev = score.get(str);
                    if(prev < s){
                        score.put(str, s);
                    }
                } else {
                    score.put(str,s);
                }
            }

            for(String key : score.keySet()){
                String[] tmp = key.split(" ");
                int id = Integer.parseInt(tmp[0]);

                team[id].score += score.get(key);
            }

            Arrays.sort(team);

            int ans = 1;
            for(int i=0; i<n; i++){
                if(team[i].id == t){
                    break;
                }
                ans++;
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Team implements Comparable<Team>{
        int id;
        int lastIdx;
        int submit;
        int score;

        Team(int id, int lastIdx, int submit, int score){
            this.id = id;
            this.lastIdx = lastIdx;
            this.submit = submit;
            this.score = score;
        }

        @Override
        public int compareTo(Team t){
            if(this.score == t.score){
                if(this.submit == t.submit){
                    return this.lastIdx - t.lastIdx;
                }
                return this.submit - t.submit;
            }
            return t.score - this.score;
        }
    }
}
