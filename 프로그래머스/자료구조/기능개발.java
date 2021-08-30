import java.util.*;

class Programmers1 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int i=0; i<speeds.length; i++){
            int rest = (int)Math.ceil((100-progresses[i]) / (double)speeds[i]);
            
            if (!q.isEmpty() && q.peek() < rest) {
                list.add(q.size());
                q.clear();
            }

            q.offer(rest);
        }
        
        list.add(q.size());

        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}