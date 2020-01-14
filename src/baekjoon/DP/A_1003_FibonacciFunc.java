package baekjoon.DP;

import java.io.*;

public class A_1003_FibonacciFunc {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int [] test = new int [T];
        for (int i = 0; i < T; i++) {
            test[i] = Integer.parseInt(br.readLine());
        }
        int [][] fibo = new int [41][3];
        fibo[0][0] = 0;
        fibo[0][1] = 1;
        fibo[0][2] = 0;
        fibo[1][0] = 1;
        fibo[1][1] = 0;
        fibo[1][2] = 1;
        for (int i = 2; i < 41; i++) {
            fibo[i][0] = fibo[i-1][0] + fibo[i-2][0];
            fibo[i][1] = fibo[i-1][1] + fibo[i-2][1];
            fibo[i][2] = fibo[i-1][2] + fibo[i-2][2];
        }
        for (int i = 0; i < T; i++) {
            bw.write(fibo[test[i]][1]+ " "+ fibo[test[i]][2]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
