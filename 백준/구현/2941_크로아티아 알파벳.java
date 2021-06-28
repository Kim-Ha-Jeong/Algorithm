import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] convert = {"dz=","c=","c-","d-","lj","nj","s=","z="};
		
		String str = br.readLine().trim();
		
		for(int i=0; i<convert.length; i++) {
			if(str.contains(convert[i]))
				str = str.replaceAll(convert[i], "*");
		}
		
		int count = str.length();
		System.out.println(count);
	}

}
