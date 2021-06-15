import java.util.Queue;
import java.util.LinkedList;
class Bruteforce_2 {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(visited[i] == false) {
            	bfs(n,computers,i);
            	answer++;
            }
        }
        
        return answer;
    }
    
    public void bfs(int n, int[][] computers, int i){
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        
        while(!q.isEmpty()){
            int now = q.poll();
            
            for(int idx=0; idx<n; idx++){
                if(computers[now][idx] == 1 && visited[idx] == false){
                    visited[idx] = true;
                    q.add(idx);
                }
            }
        }
        
    }
}