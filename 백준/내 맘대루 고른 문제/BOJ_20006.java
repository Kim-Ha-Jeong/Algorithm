import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_20006 {
    static int p,m;
    static ArrayList<ArrayList<Player>> room = new ArrayList<ArrayList<Player>>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        while(p-->0){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String nick = st.nextToken();

            int size = room.size();
            Player cur = new Player(l,nick);

            if(size == 0){
                ArrayList<Player> tmp = new ArrayList<>();
                tmp.add(cur);
                room.add(tmp);
            } else {
                boolean flag = false;
                for(int i=0; i<size; i++){
                    int stand = room.get(i).get(0).level;

                    if(room.get(i).size() < m && l>=stand-10 && l<=stand+10){
                        flag = true;
                        room.get(i).add(cur);
                        break;
                    }
                }

                if(!flag){
                    ArrayList<Player> tmp = new ArrayList<>();
                    tmp.add(cur);
                    room.add(tmp);
                }
            }
        }

        int size = room.size();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<size; i++){
            ArrayList<Player> tmp = room.get(i);
            Collections.sort(tmp);

            if(tmp.size() == m){
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }

            for(Player p : tmp){
                sb.append(p.level).append(" ").append(p.nick).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Player implements Comparable<Player>{
        int level;
        String nick;

        Player(int level, String nick){
            this.level = level;
            this.nick = nick;
        }

        @Override
        public int compareTo(Player p){
            return this.nick.compareTo(p.nick);
        }
    }
}
