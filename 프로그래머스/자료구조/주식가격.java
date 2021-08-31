import java.util.*;
class Programmers3 {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<len; i++){
            q.add(prices[i]);
        }
        
        int idx = 0;
        
        while(!q.isEmpty()){
            int tmp = q.poll();
            
            for(int target : q){
                answer[idx]++;
                if(tmp > target){
                    break;
                }
            }
            
            idx++;
        }
        
        return answer;
    }
}