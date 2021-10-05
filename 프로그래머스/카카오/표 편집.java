import java.util.Stack;
class Kakao_3 {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> trash = new Stack<Integer>();
        int size = n;
        
        for(int i=0; i<cmd.length; i++){
            char command = cmd[i].charAt(0);
            
            if(command == 'U' || command == 'D'){
            	int num = Integer.parseInt(cmd[i].split(" ")[1]);
                if(command == 'U') k = k-num;
                else k = k + num;
            } else if (command == 'C'){
                trash.push(k);
                size--;
                if(k == size) {
                	k--;
                }
            } else if (command == 'Z'){
                if(trash.pop() <= k) {
                	k++;
                }
                size++;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<size; i++) {
        	answer.append("O");
        }
        
        while(!trash.isEmpty()) {
        	answer.insert(trash.pop(), "X");
        }
        
        return answer.toString();
    }
    
}