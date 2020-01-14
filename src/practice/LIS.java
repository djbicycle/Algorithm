package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class LIS {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		int[] max = new int[N];
		int[] su = new int [N];
		for (int i = 0; i < N; i++) {
			su[i] = Integer.parseInt(st.nextToken());
		}
		int base = 0;
		max[0] = 1;
		for (int i = 0; i < N; i++) {
			base = max[i];
			for (int j = i; j < N; j++) {
				if(j==i) {
					continue;
				}
				if(su[i] < su[j]) {
					max[j] = Math.max(base + 1, max[j]);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if(ans < max[i]) {
				ans = max[i];
			}
		}
		System.out.println(ans);
	}

}
