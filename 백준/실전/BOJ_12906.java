import java.util.*;
import java.io.*;

public class BOJ_12906 {
    static HashSet<String> set;
    static Queue<Hanoi> q;
    static String answer;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        set = new HashSet<>();
        q = new LinkedList<>();

        int a, b, c;
        a = b = c = 0;

        Hanoi start = new Hanoi();

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("0"))
                continue;

            for (char ch : st.nextToken().toCharArray()) {
                if (ch == 'A')
                    a++;
                if (ch == 'B')
                    b++;
                if (ch == 'C')
                    c++;
                start.tower[i].push(ch);
            }
        }

        Hanoi end = new Hanoi();
        for (int i = 0; i < a; i++)
            end.tower[0].push('A');
        for (int i = 0; i < b; i++)
            end.tower[1].push('B');
        for (int i = 0; i < c; i++)
            end.tower[2].push('C');

        answer = end.status();

        q.add(start);
        set.add(start.status());
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; ++i) {
                Hanoi now = q.poll();

                if (now.status().equals(answer)) {
                    ans = cnt;
                    return;
                }

                for (int from = 0; from < 2; ++from) {
                    for (int to = 0; to < 3; ++to) {
                        if (!now.tower[from].isEmpty()) {
                            Hanoi next = copy(now);

                            next.tower[to].push(next.tower[from].pop());
                            String statusCode = next.status();
                            if (!set.contains(statusCode)) {
                                set.add(statusCode);
                                q.offer(next);
                            }
                        }
                        if (!now.tower[to].isEmpty()) {
                            Hanoi next = copy(now);

                            next.tower[from].push(next.tower[to].pop());
                            String statusCode = next.status();
                            if (!set.contains(statusCode)) {
                                set.add(statusCode);
                                q.offer(next);
                            }
                        }

                    }
                }
            }
            cnt++;
        }
    }

    static Hanoi copy(Hanoi origin) {
        Hanoi result = new Hanoi();

        for (int i = 0; i < 3; i++) {
            for (char ch : origin.tower[i])
                result.tower[i].push(ch);
        }

        return result;
    }

    static class Hanoi {
        Stack<Character>[] tower;

        Hanoi() {
            this.tower = new Stack[3];

            for (int i = 0; i < 3; i++) {
                tower[i] = new Stack<>();
            }
        }

        String status() {
            String result = "";

            for (char c : this.tower[0])
                result += c;
            result += "/";
            for (char c : this.tower[1])
                result += c;
            result += "/";
            for (char c : this.tower[2])
                result += c;

            return result;
        }
    }

}
