import java.util.Stack;
class Imple_3 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int depth = board.length;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<moves.length; i++){
            int x = moves[i]-1;
            for(int j=0; j<depth; j++){
                if(board[j][x] != 0){
                    stack.push(board[j][x]);
                    board[j][x] = 0;
                    break;
                }
            }
            
            if(stack.size() >= 2){
                int a = stack.pop();
                int b = stack.pop();
                
                if(a == b){
                    answer += 2;
                } else {
                    stack.push(b);
                    stack.push(a);
                }
            }
        }
        
        return answer;
    }
}