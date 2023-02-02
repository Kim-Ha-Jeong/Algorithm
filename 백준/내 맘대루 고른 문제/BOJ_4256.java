import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_4256 {
    static int n;
    static int[] preorder;
    static int[] inorder;
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){
            n = Integer.parseInt(br.readLine());

            preorder = new int[n+1];
            inorder = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            traversal(0,0,n);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void traversal(int root, int s,int e){
        int rootIdx = preorder[root];
        for(int i=s; i<e; i++){
            if(inorder[i] == rootIdx){
                traversal(root+1, s, i);
                traversal(root+i+1-s, i+1,e);
                sb.append(rootIdx).append(" ");
            }
        }
    }
}
