import java.util.Queue;
import java.util.LinkedList;

class SkillCheck_1 {
    static int[] dx = {0,-1,1,0};
    static int[] dy = {-1,0,0,1};
    static boolean[][] visited;

    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        visited = new boolean[n][m];

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0,0,1));

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == n-1 && now.y == m-1){
                answer = now.cnt;
                break;
            }

            for(int i=0; i<4; i++){
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if(nx < 0 || nx >= n || ny<0 || ny>=m)
                    continue;

                if(!visited[nx][ny] && maps[nx][ny] == 1){
                    q.add(new Node(nx,ny,now.cnt+1));
                    visited[nx][ny] = true;
                }
            }
        }

        if(answer == 0)
            return -1;

        return answer;
    }

    static class Node{
        int x;
        int y;
        int cnt;
        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}