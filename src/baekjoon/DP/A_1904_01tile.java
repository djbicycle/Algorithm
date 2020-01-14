package baekjoon.DP;

import java.io.*;

public class A_1904_01tile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long [] dp = new long [N];
        long answer = 0;
        if(N > 3){
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < N; i++) {
                dp[i] = (dp[i-1] + dp[i-2]) % 15746;
            }
            answer = dp[N-1];
        } else {
            answer = dp[N-1];
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
