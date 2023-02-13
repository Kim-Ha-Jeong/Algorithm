import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7682 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuffer sb = new StringBuffer();
        while(true){
            String str = br.readLine();
            if(str.equals("end")) break;
            char[] tmp = str.toCharArray();
            char[][] tictacto = new char[3][3];
            int X = 0, O = 0;
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    tictacto[j][k] = tmp[j*3+k];
                    if(tmp[j*3+k] == 'X') X++;
                    if(tmp[j*3+k] == 'O') O++;
                }
            }

            if(!(X == O || X == O+1)) {
                sb.append("invalid\n");
                continue;
            }

            int xRowBingo = 0, xColBingo = 0, xCrossBingo = 0, oRowBingo = 0, oColBingo = 0, oCrossBingo = 0;
            for(int i=0; i<3; i++){
                int rowX = 0, rowO = 0, colX = 0, colO = 0;
                for(int j=0; j<3; j++){
                    if(tictacto[i][j] == 'X') rowX++;
                    if(tictacto[i][j] == 'O') rowO++;

                    if(tictacto[j][i] == 'X') colX++;
                    if(tictacto[j][i] == 'O') colO++;
                }

                if(rowX == 3) xRowBingo++;
                if(rowO == 3) oRowBingo++;
                if(colX == 3) xColBingo++;
                if(colO == 3) oColBingo++;
            }


            if(xRowBingo> 1 || xColBingo >1 || oRowBingo > 1 || oColBingo > 1) {
                sb.append("invalid\n");
                continue;
            }

            if(tictacto[0][0] == tictacto[1][1] && tictacto[1][1] == tictacto[2][2]){
                if(tictacto[1][1] == 'X') xCrossBingo++;
                else oCrossBingo++;
            }

            if(tictacto[0][2] == tictacto[1][1] && tictacto[1][1] == tictacto[2][0]){
                if(tictacto[1][1] == 'X') xCrossBingo++;
                else oCrossBingo++;
            }

            if(X == O){
                if(xColBingo > 0 || xCrossBingo > 0 || xRowBingo > 0){
                    sb.append("invalid\n");
                    continue;
                }
            } else if(X > O){
                if(oColBingo > 0 || oCrossBingo > 0 || oRowBingo > 0){
                    sb.append("invalid\n");
                    continue;
                }
            }

            if(X + O == 9){
                sb.append("valid\n");
                continue;
            }

            int bingo = xRowBingo + xColBingo + xCrossBingo + oRowBingo + oColBingo + oCrossBingo;
            if(bingo == 0){
                sb.append("invalid\n");
                continue;
            }

            sb.append("valid\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
