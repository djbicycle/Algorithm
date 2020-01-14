package practice;

import java.io.*;
import java.util.StringTokenizer;

public class LIS_2 {
    static int N;
    static int [] dp;
    static int [] ar;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int [N];
        dp[0] = 1;
        ar = new int [N];
        for (int i = 0; i < N; i++){
            ar[i] = Integer.parseInt(st.nextToken());
        }
        bw.write(new StringBuilder().append(getdp(N-1)).toString());
        bw.write(new StringBuilder().append("\n").toString());
        bw.close();
        br.close();
    }

    public static int getdp(int n){
        if(n > 0){
            if(ar[n] > ar[n-1]){
                dp[n] = Math.max(getdp(n-1) + 1, 1);
            } else {
                dp[n] = Math.max(1, getdp(n-1));
            }
        }
        return dp[n];
    }
}
