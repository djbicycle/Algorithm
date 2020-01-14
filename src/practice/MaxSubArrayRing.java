package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MaxSubArrayRing {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long[] maxDp = new long[N];
        long[] minDp = new long[N];
        long[] ar = new long[N];
        long max = -1000000001;
        long min = 1000000001;
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ar[i] = Long.parseLong(st.nextToken());
            sum += ar[i];
        }
        for (int i = 0; i < N; i++) {
            if (i >= 1) {
                maxDp[i] = Math.max(ar[i] + maxDp[i - 1], ar[i]);
            } else {
                maxDp[i] = ar[i];
            }
            if (i >= 1) {
                minDp[i] = Math.min(ar[i] + minDp[i - 1], ar[i]);
            } else {
                minDp[i] = ar[i];
            }
        }
        for (int i = 0; i < N; i++) {
            if (max < maxDp[i]) {
                max = maxDp[i];
            }
            if (min > minDp[i]) {
                min = minDp[i];
            }
        }
//            System.out.println(max+" , "+min);
        long answer = Math.max(0, Math.max(max, sum - min));
        bw.write(new StringBuilder().append(answer).toString());
        bw.write(new StringBuilder().append("\n").toString());
        bw.flush();
        bw.close();
        br.close();
    }
}