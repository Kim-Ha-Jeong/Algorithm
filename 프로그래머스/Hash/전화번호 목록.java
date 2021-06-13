import java.util.HashSet;
class Hash_10 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashSet<String> set = new HashSet<String>();
        
        for(String input: phone_book){
            set.add(input);
        }
        
        for(String target : phone_book){
            for(int i=0; i<target.length(); i++){
                if(set.contains(target.substring(0,i)))
                    return false;
            }
        }
        
        return answer;
    }
}