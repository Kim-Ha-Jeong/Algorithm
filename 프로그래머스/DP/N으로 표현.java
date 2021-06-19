import java.util.*;
class DP_12 {
    static HashSet<Integer>[] dp = new HashSet[9];
    public int solution(int N, int number) {
        if(N == number)
            return 1;
        
        String n = String.valueOf(N);
        for(int i=0; i<=8; i++){
            dp[i] = new HashSet<Integer>();
            if(i == 1) dp[1].add(Integer.parseInt(n));
            if(i<2) continue;
            n+=String.valueOf(N);
            dp[i].add(Integer.parseInt(n));
            for(int j=1; j<i; j++){
                calc(j,i);
                if(dp[i].contains(number))
                    return i;
            }
        }
        
        return -1;
    }
    
    static void calc(int a, int b){
        Iterator<Integer> listA = dp[a].iterator();
        Iterator<Integer> listB = dp[b-a].iterator();
        while(listA.hasNext()){
            int numA = listA.next();
            
            while(listB.hasNext()){
                int numB = listB.next();
                dp[b].add(numA+numB);
                dp[b].add(numA-numB);
                dp[b].add(numA*numB);
                if(numB != 0)
                    dp[b].add(numA/numB);
            }
            listB = dp[b-a].iterator();
        }
    }
}