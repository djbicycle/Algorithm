package baekjoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

public class partSum_2042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        node_ps nd = new node_ps(1, N);
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            nd.setSu(i, Integer.parseInt(st.nextToken()), 0);
        }
        int type, n, su;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            su = Integer.parseInt(st.nextToken());
            if(type == 1){
                nd.setSu(n, su, 1);
            } else {
                System.out.println("?");
                System.out.println(nd.getSum(n, su));
            }
        }

    }
}

class node_ps{
    int su;
    int start;
    int end;
    int mid;
    long sum;
    node_ps left;
    node_ps right;

    public node_ps(int start, int end) {
        this.start = start;
        this.end = end;
        if(start != end){
            this.mid = (int) Math.floor((start+end)/2);
            left = new node_ps(start, this.mid);
            right = new node_ps(this.mid+1, end);
        }
    }

    public void setSu(int order, int su, int type){
        if(type == 0){
            this.sum += su;
        } else {
            this.sum = sum - this.su + su;
        }
        if(this.start == order && this.end == order){
            this.su = su;
        } else {
            if(order <= this.mid){
                left.setSu(order, su, type);
            } else {
                right.setSu(order, su, type);
            }
        }
    }

    public long getSum(int start, int end){
        System.out.println("es: "+start+" "+end);
        long answer = 0;
        if(this.start == start && this.end == end){
            answer = this.sum;
        } else {
            if(end <= this.mid){
                System.out.println("11");
                answer += left.getSum(start, end);
            } else if (start > this.mid){
                System.out.println("22");
                answer += right.getSum(start, end);
            } else {
                System.out.println("33");
                answer += left.getSum(start, this.mid);
                System.out.println("44");
                answer += right.getSum(this.mid+1, end);
            }
        }
        System.out.println("answer= "+answer);
        return answer;
    }
}