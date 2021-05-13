import java.io.*;
import java.util.*;

public class Automata {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		HashMap<String, ArrayList<String>> ruleMap = new HashMap<String, ArrayList<String>>();

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(">");
			ArrayList<String> vars = new ArrayList<String>();

			if (ruleMap.containsKey(s[1]))
				vars = ruleMap.get(s[1]);
			vars.add(s[0]);
			ruleMap.put(s[1], vars);

		}

		HashMap<Integer, ArrayList<String>> parseVarMap = new HashMap<Integer, ArrayList<String>>();
		String w = br.readLine();
		int length = w.length();

		for (int len = 1; len <= length; len++) {
			for (int i = 1, j = len; j <= length; i++, j++) {
				ArrayList<String> newVars = new ArrayList<String>();
				int newkey = i * length + j;
				if (i == j) {
					String findkey = w.substring(i - 1, i);
					ArrayList<String> ruleVar = ruleMap.get(findkey);

					if (ruleVar != null && !ruleVar.isEmpty()) {
						for (String var : ruleVar) {
							newVars.add(var);
						}
						parseVarMap.put(newkey, newVars);
					}
				}
				for (int k = i; k < j; k++) {

					Integer key1 = i * length + k;
					Integer key2 = (k + 1) * length + j;

					for (String var1 : parseVarMap.get(key1)) {
						for (String var2 : parseVarMap.get(key2)) {
							String findkey = var1 + var2;
							ArrayList<String> ruleVar = ruleMap.get(findkey);
							if (ruleVar != null && !ruleVar.isEmpty()) {
								for (String var : ruleVar) {
									if (!newVars.contains(var))
										newVars.add(var);
								}
							}
						}
					}

					parseVarMap.put(newkey, newVars);
				}
			}
		}

		ArrayList<String> fin = new ArrayList<String>();
		fin = parseVarMap.get(length*2);
		
		if (fin.contains("S"))
			System.out.println("Accept");
		else
			System.out.println("Reject");
	}

}
