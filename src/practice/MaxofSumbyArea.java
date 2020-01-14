package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MaxofSumbyArea {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long [][] ar = new long [N][N];
        long max = 0;
        for (int i = 0; i < N; i++) {
			ar[0][i] = Long.parseLong(st.nextToken());
			max += ar[0][i];
		}
        for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
				if(j+1 >= N) {
					ar[i][j] = ar[i-1][j] + ar[i-1][j+1-N];
				} else {
					ar[i][j] = ar[i-1][j] + ar[i-1][j+1];
				}
				if(max < ar[i][j]) {
					max = ar[i][j];
				}
			}
		}
        bw.write(max + "\n");
        bw.flush();
        bw.close();
	}
}

