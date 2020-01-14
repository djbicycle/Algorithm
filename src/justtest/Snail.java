package justtest;

import java.io.*;
import java.util.StringTokenizer;

public class Snail {
    static int [][] ar;
    static int goal;
    static int value;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String s = st.nextToken();
        ar = new int [N][N];
        goal = (int) Math.pow(N,2);
        value = 1;
        fillup(N , s);
        bw.close();
        br.close();
    }

    static void fillup(int n, String s){
        int rotate = 0;
        int i = 0;
        int j = 0;
        int tmpi = 0;
        int tmpj = 0;
        int value = 1;
        if(s.equals("L")){
            // (0,N-1) L D R U -> j--, i++, j++, i--
            while(rotate <= n){
                i = rotate;
                j = n - 1;
                j = fillRowReturnNextStartColumn(-1, i, j);
                i = rotate + 1;
                tmpi = fillColumnReturnNextRow(1, i, j);

                n = fillRowReturnNextStartColumn(1, i, j);
                n = fillColumnReturnNextRow(-1, i, j);
                if(n == goal){
                    break;
                }
            }
        } else {
            // (0,0) R D L U --> j++, i++, j--, i--
        }
    }

    static int inputValue(){

        return 0;
    }

    static int fillRowReturnNextStartColumn(int direction, int i, int j){
        if(ar[i][j] > 0){
            return value;
        } else {
            while(ar[i][j] > 0){
                ar[i][j] =value;
                j += (1 * direction);
                if(j < 0 || j >= N){
                    break;
                }
            }
            j += (-1 * direction);
            return j;
        }
    }

    static int fillColumnReturnNextRow(int direction, int i, int j){
        if(ar[i][j] > 0){
            return value;
        } else {
            while(ar[i][j] > 0){
                ar[i][j] =value;
                i += (1 * direction);
            }
            return i;
        }
    }

    // R: j++
    // L: j--
    // U: i--
    // D: i++

    // ij 결정변수, -+결정변수
}
