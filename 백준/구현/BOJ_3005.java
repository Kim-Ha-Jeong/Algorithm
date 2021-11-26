import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_3005 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);

		String[][] cross = new String[r][c];
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < r; i++) {
			s = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				cross[i][j] = s[j];
			}
		}

		int length = 0;

		for (int i = 0; i < r; i++) {
			String tmp = "";
			length = 0;
			for (int j = 0; j < c; j++) {
				if (cross[i][j].equals("#")) {
					if (length >= 2) {
						list.add(tmp);
					}
					length = 0;
					tmp = "";
				} else {
					tmp += cross[i][j];
					length++;
				}
			}
			if (tmp.length() >= 2)
				list.add(tmp);
		}

		for (int i = 0; i < c; i++) {
			String tmp = "";
			length = 0;
			for (int j = 0; j < r; j++) {
				if (cross[j][i].equals("#")) {
					if (length >= 2) {
						list.add(tmp);
					}
					length = 0;
					tmp = "";
				} else {
					tmp += cross[j][i];
					length++;
				}
			}
			if (tmp.length() >= 2)
				list.add(tmp);
		}

		Collections.sort(list);

		System.out.println(list.get(0));
	}

}
