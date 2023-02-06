import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_4659 {
    static char[] password;
    static boolean[] alpha;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        while(true){
            String str = br.readLine();
            password = str.toCharArray();
            alpha = new boolean[26];

            if(str.equals("end")){
                break;
            }

            int len = password.length;
            int v = 0, s = 0;
            boolean flag = true;

            for(int i=0; i<len; i++){
                alpha[password[i] - 'a'] = true;
                if(isVowel(password[i])){
                    v++;
                    s = 0;
                } else {
                    s++;
                    v = 0;
                }

                if(i+1 < len){
                    if(password[i] == password[i+1]){
                        if(password[i] != 'e' && password[i] != 'o'){
                            flag = false;
                            break;
                        }
                    }
                }

                if(v == 3 || s == 3){
                    flag = false;
                    break;
                }
            }


            if(!alpha['a' - 'a'] && !alpha['e' - 'a'] && !alpha['i' - 'a'] && !alpha['o' - 'a'] && !alpha['u' - 'a']){
                flag = false;
            }

            sb.append("<").append(str);
            if(flag){
                sb.append("> is acceptable.\n");
            } else {
                sb.append("> is not acceptable.\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean isVowel(char c){
        if(c == 'a' || c == 'e' || c == 'o' || c =='i' || c == 'u') return true;
        return false;
    }
}
