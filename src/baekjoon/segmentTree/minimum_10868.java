package baekjoon.segmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class minimum_10868 {
    static int N;
    static int M;
    static int [] value;
    static int [][] ar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ar = new int [((int) (Math.log(N)/Math.log(2)))+1][N+1];
        value = new int [N+1];
//        System.out.println();
        for (int i = 1; i <= N; i++) {
            value[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
//            System.out.println();
            addValue(0,1, N, i, value[i]);
        }
//        System.out.println();
        int x, y;
        int answer = 0;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            bw.write(new StringBuilder().append(getMin(1, N, x, y)).append("\n").toString());
            bw.flush();
        }
        // 입력할 때 값 잘 찾아들어갔는지 확인
//        for (int i = 0; i< ar.length;i++){
//            System.out.print(i+"\t");
//            for (int j = 1; j< N +1; j++){
//                System.out.print(ar[i][j]+"\t");
//            }
//            System.out.println();
//        }
        br.close();
        bw.close();
    }
    static int getMin(int start, int end, int mStart, int mEnd){
        if(start == end){
            return value[start];
        }
        if(ar[start][end] > 0){
            return ar[start][end];
        } else {
            ArrayList<Integer> al = new ArrayList<>();
            int mid = (int) Math.round((start+end) / 2);
            int ans;
            if(mEnd <= mid){
                ans = getMin(start, mid, mStart, mEnd);
                al.add(ans);
                ar[mStart][mEnd] = ans;
            } else if (mStart > mid){
                ans = getMin(mid + 1, end, mStart, mEnd);
                al.add(ans);
                ar[mStart][mEnd] = ans;
            } else {
                ans = getMin(start, mid, mStart, mid);
                al.add(ans);
                ar[mStart][mid] = ans;
                ans = getMin(mid + 1, end, mid + 1, mEnd);
                al.add(ans);
                ar[mid+1][mEnd] = ans;
            }
            Collections.sort(al);
            return al.get(0);
        }
    }

    static void addValue(int row, int start, int end, int index, int n){
        if(start == end || row >= (int) (Math.log(N)/Math.log(2))+1){
            return;
        }
        if(ar[row][start] > n || ar[row][start] == 0){
//            System.out.println("input: "+row+" "+start+", "+ar[row][start]+" vs "+n);
            ar[row][start] = n;
        }
        int mid = (int) Math.round((start+end) / 2);
//        System.out.println(row+" "+start+" "+mid+" "+end);
        if(index <= mid){
            addValue(row+1, start, mid, index, n);
        } else {
            addValue(row+1, mid+1, end, index, n);
        }
    }
}
