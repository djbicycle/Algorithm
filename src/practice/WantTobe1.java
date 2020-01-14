package practice;

import java.util.Scanner;

public class WantTobe1 {
	static int [] dp;
	static int loop;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		if(N==3 || N ==2) {
			System.out.println("1");
			return;
		} else if (N==1) {
			System.out.println("0");
			return;
		}
		dp = new int [N+1];
		
		System.out.println(godp(N));
	}
	
	public static int godp(int n) {
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
//		int temp = dp[n];
//		System.out.println("숫자 : "+n);
		if(n > 3) {
			if(n%3 == 0 && n%2 == 0) {
				dp[n] = Math.min(godp(n/3), godp(n/2)) + 1;
			} else if (n%3 == 0 && n%2 != 0) {
				dp[n] = Math.min(godp(n/3), godp(n-1)) + 1;
			} else if (n%3 != 0 && n%2 == 0) {
				dp[n] = Math.min(godp(n/2), godp(n-1)) + 1;
			} else {
				dp[n] = godp(n-1) + 1;
			}
		}
//		System.out.println("리턴 : "+dp[n]);
//		if(dp[n] != 0) {
//			return Math.min(temp, dp[n]);
//		} else {
//			return temp;
//		}
		return dp[n];
	}
	
}
