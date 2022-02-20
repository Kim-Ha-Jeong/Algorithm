import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Collections;

public class BOJ_1713 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] student = new boolean[101];
        int[] recommend = new int[101];
        LinkedList<Integer> photo = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        while (m-- > 0) {
            int person = Integer.parseInt(st.nextToken());

            if (student[person]) {
                recommend[person]++;
            } else if (photo.size() < n) {
                photo.add(person);
                recommend[person]++;
                student[person] = true;
            } else if (photo.size() == n) {
                int min = Integer.MAX_VALUE;
                int minIdx = 0;
                for (int i = 0; i < photo.size(); i++) {
                    int tmp = photo.get(i);
                    if (min > recommend[tmp]) {
                        min = recommend[tmp];
                        minIdx = i;
                    }
                }

                int remove = photo.remove(minIdx);
                student[remove] = false;
                recommend[remove] = 0;

                photo.add(person);
                recommend[person]++;
                student[person] = true;
            }

        }

        Collections.sort(photo);
        for (int tmp : photo) {
            sb.append(tmp).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
