package baekjoon.DP;

import java.io.*;

public class A_2748_Fibonacchi2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long [] fibo = new long [N+1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= N; i++){
            fibo[i] = fibo[i-1] + fibo[i-2];
        }
        bw.write(String.valueOf(fibo[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
