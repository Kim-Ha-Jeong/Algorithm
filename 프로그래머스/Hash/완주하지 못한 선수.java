import java.util.HashMap;
class Hash_6 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> marathon = new HashMap<>();
        
        for(String s : participant){
            if(marathon.containsKey(s))
                marathon.put(s, marathon.get(s)+1);
            else
                marathon.put(s, 1);
        }
        
        for(String s : completion){
            marathon.put(s, marathon.get(s)-1);
        }
        
        for(String s: marathon.keySet()){
            if(marathon.get(s) != 0)
                answer+=s;
        }
        
        return answer;
    }
}