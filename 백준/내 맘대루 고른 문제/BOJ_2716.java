import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2716 {
    static int n,A,B;
    static Node[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if(n == 0 && A == 0 && B == 0) break;

            arr = new Node[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int balloon = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[i] = new Node(balloon, a, b);
            }
            int ans = 0;

            Arrays.sort(arr, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Math.abs(o2.a - o2.b) - Math.abs(o1.a - o1.b);
                }
            });

            for (int i = 0; i < n; i++) {
                Node now = arr[i];

                if (now.a < now.b) {
                    if (A >= now.balloon) {
                        A -= now.balloon;
                        ans += now.balloon * now.a;
                    } else {
                        int rest = now.balloon - A;
                        ans += A * now.a;
                        A = 0;
                        ans += rest * now.b;
                        B -= rest;
                    }
                } else {
                    if (B >= now.balloon) {
                        B -= now.balloon;
                        ans += now.balloon * now.b;
                    } else {
                        int rest = now.balloon - B;
                        ans += B * now.b;
                        B = 0;
                        ans += rest * now.a;
                        A -= rest;
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        int balloon;
        int a;
        int b;

        Node(int balloon, int a, int b){
            this.balloon = balloon;
            this.a = a;
            this.b = b;
        }
    }
}
