import java.util.*;
public class Kakao_2 {
	static int[] dx = {1,0,-1,0,2,0,-2,0,1,-1,-1,1};
    static int[] dy = {0,1,0,-1,0,2,0,-2,1,-1,1,-1};
    static boolean[][] visited;
    static char[][] waitingRoom;
    static Queue<Node> q;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int tc = 5;
        
        for(int i=0; i<tc; i++){
            waitingRoom = new char[5][5];
            visited = new boolean[5][5];
            q = new LinkedList<Node>();
            
            for(int j=0; j<5; j++){
                waitingRoom[j] = places[i][j].toCharArray();
            }
            
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    if(waitingRoom[j][k] == 'P'){
                        q.add(new Node(j, k));
                    }
                }
            }
            
            answer[i] = bfs();
            
            
        }
        return answer;
    }
    
    static int bfs(){
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int i=0; i<12; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                
                if(waitingRoom[nx][ny] == 'P'){
                    if(i < 4) return 0;
                    else if(i<8){
                        int mx = cur.x + dx[i] / 2;
                        int my = cur.y + dy[i] / 2 ;
                        if(waitingRoom[mx][my] != 'X') return 0;
                    } else {
                        if(waitingRoom[cur.x + dx[i]][cur.y] != 'X' || waitingRoom[cur.x][cur.y + dy[i]] != 'X') return 0;
                    }
                }
            }
        }
        
        return 1;
    }
    
    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
