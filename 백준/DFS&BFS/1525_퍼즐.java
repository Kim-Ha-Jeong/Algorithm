import java.io.*;
import java.util.*;

public class BOJ_1525 {
	static HashMap<Integer, Integer> map;
	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start = 0;
		for (int i = 0; i < 3; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				int tmp = Integer.parseInt(s[j]);
				if (tmp == 0)
					tmp = 9;
				start = start * 10 + tmp;
			}
		}

		map = new HashMap<Integer, Integer>();
		map.put(start, 0);

		bfs(start);

	}

	public static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();

		q.add(n);

		while (!q.isEmpty()) {
			int now = q.poll();
			String nowStr = String.valueOf(now);

			int zero = nowStr.indexOf('9');
			int x = zero / 3;
			int y = zero % 3;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int move = 3 * nx + ny;

				if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3)
					continue;

				StringBuffer next = new StringBuffer(nowStr);
				char tmp = next.charAt(move);
				next.setCharAt(move, '9');
				next.setCharAt(zero, tmp);

				int nextNum = Integer.parseInt(next.toString());

				if (!map.containsKey(nextNum)) {
					map.put(nextNum, map.get(now) + 1);
					q.add(nextNum);
				}
			}

		}

		if (map.containsKey(123456789))
			System.out.println(map.get(123456789));
		else
			System.out.println(-1);

	}

}