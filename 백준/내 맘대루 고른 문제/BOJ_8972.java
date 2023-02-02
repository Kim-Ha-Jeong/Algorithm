import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_8972 {
    static int r,c;
    static boolean flag = true;
    static int[][] map;
    static int[] dx = {1,1,1,0,0,0,-1,-1,-1};
    static int[] dy = {-1,0,1,-1,0,1,-1,0,1};
    static Node jongsu;
    static HashMap<Integer,Node> robot = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        int idx = 1;
        for(int i=0; i<r; i++){
            char[] ch = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                if(ch[j] == 'I'){
                    map[i][j] = -1;
                    jongsu = new Node(i,j);
                } else if(ch[j] == 'R'){
                    map[i][j] = idx;
                    robot.put(idx, new Node(i,j));
                    idx++;
                }
            }
        }

        char[] command = br.readLine().toCharArray();
        idx = 0;

        while(idx < command.length){
            int d = (command[idx] - '0') - 1;
            move(d);
            if(!flag) break;
            moveRobot();
            if(!flag) break;
            idx++;
        }

        if(!flag){
            sb.append("kraj ").append(idx+1);
        } else {
            for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    if(map[i][j] == -1){
                        sb.append("I");
                    } else if(map[i][j] > 0){
                        sb.append("R");
                    } else {
                        sb.append(".");
                    }
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void move(int d){
        int nx = jongsu.x + dx[d];
        int ny = jongsu.y + dy[d];

        if(map[nx][ny] > 0) {
            flag = false;
            return;
        }

        map[jongsu.x][jongsu.y] = 0;
        jongsu = new Node(nx,ny);
        map[nx][ny] = -1;
    }

    static void moveRobot(){
        int[][] tmp = copy();
        HashSet<Integer> remove = new HashSet<>();

        for(int key : robot.keySet()){
            Node ro = robot.get(key);
            int min = Integer.MAX_VALUE;
            int index = -1;

            for(int j=0; j<9; j++){
                if(j == 4) continue;
                int nx = ro.x + dx[j];
                int ny = ro.y + dy[j];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                int abs = Math.abs(nx - jongsu.x) + Math.abs(ny - jongsu.y);
                if(min > abs){
                    min = abs;
                    index = j;
                }
            }
            int nx = ro.x + dx[index];
            int ny = ro.y + dy[index];

            if(tmp[nx][ny] == -1){
                flag = false;
                return;
            }

            robot.put(key, new Node(nx,ny));
            if(tmp[nx][ny] > 0){
                remove.add(key);
                remove.add(tmp[nx][ny]);
                continue;
            }
            tmp[nx][ny] = key;
        }

        for(int re : remove){
            Node now = robot.get(re);
            tmp[now.x][now.y] = 0;
            robot.remove(re);
        }

        map = tmp;
    }

    static int[][] copy(){
        int[][] tmp = new int[r][c];
        tmp[jongsu.x][jongsu.y] = -1;
        return tmp;
    }

    static class Node {
        int x;
        int y;

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }


}
