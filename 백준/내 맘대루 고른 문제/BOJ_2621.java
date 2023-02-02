import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2621 {
    static char[][] card;
    static int[] num = new int[5];
    static int[] color = new int[4];

    static ArrayList<Node> same = new ArrayList<>();

    static int seq = 1;
    static int csame = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        card = new char[5][2];

        HashMap<Integer, Integer> tmp = new HashMap<>();
        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            
            card[i][0] = st.nextToken().charAt(0);
            card[i][1] = st.nextToken().charAt(0);
            setColor(card[i][0]);
            num[i] = card[i][1] - '0';

            if(tmp.containsKey(num[i])){
                tmp.put(num[i], tmp.get(num[i])+1);
            } else {
                tmp.put(num[i], 1);
            }
        }

        for(int a : tmp.keySet()){
            same.add(new Node(a, tmp.get(a)));
        }
        
        Arrays.sort(num);
        Arrays.sort(color);

        for(int i=0; i<4; i++){
            int cnt = 1;
            for(int j=i; j<4; j++){
                if(num[j] + 1 != num[j+1]) break;
                cnt++;
            }
            seq = Math.max(seq, cnt);
        }

        csame = color[3];
        Collections.sort(same);

        int ans = solve();

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static int solve(){
        int score = 0;

        int a = -1,b = -1;
        int len = same.size();

        if(len >= 1){
            a = same.get(0).cnt;
        }

        if(len >= 2){
            b = same.get(1).cnt;
        }

        if(csame == 5 && seq == 5){
            score = Math.max(score, num[4]+900);
        }

        if(a == 4){
            int tmp = same.get(0).num;
            score = Math.max(score, tmp + 800);
        }

        if(a == 3 && b == 2){
            int tmpA = same.get(0).num;
            int tmpB = same.get(1).num;

            score = Math.max(score, tmpA*10+tmpB+700);
        }

        if(csame == 5){
            score = Math.max(score, num[4]+600);
        }

        if(seq == 5){
            score = Math.max(score, num[4]+500);
        }

        if(a == 3){
            int tmpA = same.get(0).num;
            score = Math.max(score, tmpA+400);
        }

        if(a == 2 && b == 2){
            int tmpA = same.get(0).num;
            int tmpB = same.get(1).num;
            int max = Math.max(tmpA, tmpB);
            int min = Math.min(tmpA, tmpB);

            score = Math.max(score, max*10+min+300);
        }

        if(a == 2){
            int tmpA = same.get(0).num;
            score = Math.max(score, tmpA+200);
        }

        if(score == 0){
            score = num[4] + 100;
        }

        return score;
    }

    static void setColor(char c){
        switch(c){
            case 'R':
                color[0]++;
                break;
            case 'B':
                color[1]++;
                break;
            case 'G':
                color[2]++;
                break;
            case 'Y':
                color[3]++;
                break;
        }
    }

    static class Node implements Comparable<Node>{
        int num;
        int cnt;

        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n){
            return n.cnt - this.cnt;
        }
    }
}
