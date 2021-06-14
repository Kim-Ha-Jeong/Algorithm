class Bruteforce_1 {
    static int count = 0;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(numbers, target, 0, 0);
        return answer = count;
    }
    
    public void dfs(int[] numbers, int target, int depth, int ret){
        if(depth == numbers.length){
            if(ret == target)
                count++;
            return;
        } else {
            dfs(numbers, target, depth+1, ret+numbers[depth]);
            dfs(numbers, target, depth+1, ret-numbers[depth]);
        }
    }
}