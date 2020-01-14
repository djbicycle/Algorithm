package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RGBdistance {
	public static long [][] min;
	public static int [][] ar;
	public static long [] ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		min = new long [N][3];
		ar = new int [N][3];
		ans = new long [N];
		String [] st = new String [3];
		for (int i = 0; i < N; i++) {
			st = br.readLine().split(" ");
			ar[i][0] = Integer.parseInt(st[0]);//R
			ar[i][1] = Integer.parseInt(st[1]);//G
			ar[i][2] = Integer.parseInt(st[2]);//B
		}
		
		long red = 0;
		long green = 0;
		long blue = 0;
		for (int i = 0; i < N; i++) {
			if(i == 0) {
				red = ar[i][0];
				green = ar[i][1];
				blue = ar[i][2];
			} else {
				red = ar[i][0] + Math.min(min[i-1][1], min[i-1][2]);
				green = ar[i][1] + Math.min(min[i-1][0], min[i-1][2]);
				blue = ar[i][2] + Math.min(min[i-1][0], min[i-1][1]);
			}
			min[i][0] = red;
			min[i][1] = green;
			min[i][2] = blue;
			ans[i] = Math.min(red, Math.min(green, blue));
		}
		System.out.println(ans[N-1]);
	}
}
