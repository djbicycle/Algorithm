package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class NumberTriangle {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] dp = new int [N];
		int tmp = 0;
		int prev = 0;
		int pretm = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				tmp = Integer.parseInt(st.nextToken());
				if(j == 0) {
					prev = dp[j];
					dp[j] = dp[j]+ tmp;
//					System.out.println("prev = "+prev+" DP[j] = "+dp[j]);
				} else if ( j == i ) {
//					System.out.println("tmmp = "+tmp);
					dp[j] = prev + tmp;
//					System.out.println("DP[j] = "+dp[j]);
				} else  {
//					System.out.println("계산전 prev = "+prev+" dp[j] = "+dp[j] + " tmp = "+tmp);
					pretm = dp[j];
					dp[j] = Math.max(prev + tmp, dp[j] + tmp);
					prev = pretm;
//					System.out.println("계산후 prev = "+prev+" DP[j] = "+dp[j]);
				}
			}
//			System.out.println();
//			for (int j = 0; j <= i; j++) {
//				System.out.print("=== "+dp[j]);
//			}
//			System.out.println();
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
		}
		bw.write(max + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
	
}
