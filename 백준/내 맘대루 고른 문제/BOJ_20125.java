import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_20125 {
    static int n;
    static char[][] map;
    static boolean[][] row;
    static boolean[][] col;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[] heart = new int[2];
    static ArrayList<Node> part = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        row = new boolean[n][n];
        col = new boolean[n][n];

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == '*' && !row[i][j] && !col[i][j]){
                    int x = i, y = j;
                    Node now = new Node(x,y);
                    boolean flag = false;
                    for(int d=0; d<4; d++){
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if(map[nx][ny] == '*'){
                            flag = true;
                            if(d < 2){
                                solve(x,y,d,col, row, now);
                            } else {
                                solve(x,y,d,row, col, now);
                            }
                            break;
                        }
                    }

                    if(!flag){
                        part.add(now);
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        sb.append(heart[0]+1).append(" ").append(heart[1]+1).append("\n");

        Node par = part.get(1);
        sb.append(heart[1] - par.startY).append(" ");
        sb.append(par.endY - heart[1]).append(" ");

        Node huri = part.get(0);
        sb.append(huri.endX - heart[0]).append(" ");

        Node leftLeg = part.get(2);
        sb.append(leftLeg.endX - leftLeg.startX+1).append(" ");
        Node rightLeg = part.get(3);
        sb.append(rightLeg.endX - rightLeg.startX+1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static class Node {
        int startX;
        int startY;
        int endX;
        int endY;
        
        Node(int startX, int startY){
            this.startX = startX;
            this.startY = startY;
            this.endX = startX;
            this.endY = startY;
        }
    }
    
    static void solve(int x, int y, int d, boolean[][] v, boolean[][] rest, Node tmp){
        rest[x][y] = false;
        v[x][y] = true;
        
        while(true){
            x += dx[d];
            y += dy[d];
            
            if(x < 0 || x >= n || y <0 || y>= n) break;
            if(map[x][y] == '_') break;
            if(v[x][y]) break;
            
            v[x][y] = true;
            if(rest[x][y]) {
                heart[0] = x;
                heart[1] = y;
            }
        }
        
        tmp.endX = x - dx[d];
        tmp.endY = y - dy[d];
        
        part.add(tmp);
    }
}
