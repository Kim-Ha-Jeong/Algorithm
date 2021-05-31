import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Hash_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, String> company = new HashMap<>();

		int n = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");

			if (s[1].equals("enter"))
				company.put(s[0], "enter");
			else
				company.put(s[0], "leave");
		}

		for (String key : company.keySet()) {
			if (company.get(key).equals("enter")) {
				list.add(key);
			}
		}

		Collections.sort(list);

		for(int i=list.size()-1; i>=0; i--)
			System.out.println(list.get(i));
	}

}
