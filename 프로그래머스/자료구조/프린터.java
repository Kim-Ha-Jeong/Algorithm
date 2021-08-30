import java.util.*;

class Programmers2 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Priority> q = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++){
            q.add(new Priority(i, priorities[i]));
        }
        
        while(!q.isEmpty()){
            int cur = q.peek().pri;
            boolean flag = false;
            
            for(Priority p : q){
                if(cur < p.pri) flag = true;
            }
            
            if(flag) q.add(q.poll());
            else {
                if(q.poll().idx == location){
                    answer = priorities.length - q.size();
                }
            }
        }
        return answer;
    }
    
    public static class Priority {
        int idx;
        int pri;
        
        Priority(int idx, int pri){
            this.idx = idx;
            this.pri = pri;
        }
    }
}