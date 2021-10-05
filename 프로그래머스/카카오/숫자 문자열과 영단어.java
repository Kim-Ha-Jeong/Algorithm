import java.util.ArrayList;
class Kakao_1 {
    public int solution(String s) {
        String[] words = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        ArrayList<Integer> list = new ArrayList<>(); 
        
        int idx = 0;
        while(true){
            if(idx >= s.length()){
                break;
            }
            
            if(s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                idx++;
                continue;
            }
            
            String sub = s.substring(idx, idx+2);
            for(int i=0; i<10; i++){
                if(sub.equals(words[i].substring(0,2))){
                    s = s.replaceFirst(words[i], "*");
                    list.add(i);
                    break;
                }
            }
            idx++;
        }
        
        idx = 0;
        String ret = "";
        for(int i=0 ; i<s.length(); i++){
            if(s.charAt(i) == '*'){
                ret += Integer.toString(list.get(idx));
                idx++;
            } else {
                ret += Character.toString(s.charAt(i));
            }
        }
        
        return Integer.parseInt(ret);
    }
}