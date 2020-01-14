package baekjoon.DP;

import java.io.*;

public class A_1912_continousSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String [] su = new String[N];
        int [] sum = new int [N];
        su = br.readLine().split(" ");
        int max = -1001;
        for (int i = 0; i < N; i++) {
            if(i > 0){
                sum[i] = Math.max(sum[i-1]+Integer.parseInt(su[i]), Integer.parseInt(su[i]));
            } else {
                sum[i] = Integer.parseInt(su[i]);
            }
            max = Math.max(max, sum[i]);
        }
        bw.write(String.valueOf(max));
        bw.close();
        br.close();
    }
}
