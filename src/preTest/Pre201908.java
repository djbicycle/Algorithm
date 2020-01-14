package preTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Pre201908 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Test = Integer.parseInt(st.nextToken());
		for (int t = 0; t < Test; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			long[][] wh = new long[N + 1][4];
			int x = 0;
			int y = 0;
			int k = 0;
			int c = 0;
			String s = "";
			long max = 0; // 판매 요청 시점 판매 가능한 최대 세트 수량
			long[] acc = new long[N + 1]; // 판매 후 시점 판매된 세트 갯수 저장
			long soldSet = 0; // 누적 판매 세트 개수
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				s = st.nextToken();
				if (s.equals("1")) {
					x = Integer.parseInt(st.nextToken()); // x번 판매점부터
					y = Integer.parseInt(st.nextToken()); // y번 판매점까지
					k = Integer.parseInt(st.nextToken()); // k번 상품을
					c = Integer.parseInt(st.nextToken()); // c개 더한다
					// 납품
					for (int j = x; j <= y; j++) {
						wh[j][k] += c;
					}
				} else if (s.equals("2")) {
					x = Integer.parseInt(st.nextToken()); // x번 판매점에서
					c = Integer.parseInt(st.nextToken()); // c개 세트 판매 요청
					//판매 
					//1,2,3 중 최수숫자를 골라서 세트 판매 개수 max[x] 구함.
					max = Math.min(wh[x][1], Math.min(wh[x][2], wh[x][3]));
					if (c > max) {
						acc[x] += max;
						wh[x][1] = wh[x][1] - max;
						wh[x][2] = wh[x][2] - max;
						wh[x][3] = wh[x][3] - max;
						if (acc[x] == 9) {
							System.out.print("x = " + x + " " + wh[x][1] + " " + wh[x][2] + " " + wh[x][3]);
						}
						System.out.println();
					} else {
						//x번 판매점의 c 개 세트 판매 가능한만큼 빼기
						acc[x] += c;
						wh[x][1] = wh[x][1] - c;
						wh[x][2] = wh[x][2] - c;
						wh[x][3] = wh[x][3] - c;
						if (acc[x] == 9) {
							System.out.print("x = " + x + " " + wh[x][1] + " " + wh[x][2] + " " + wh[x][3]);
						}
						System.out.println();
					}
				} else {
					x = Integer.parseInt(st.nextToken()); // x번 판매점부터
					y = Integer.parseInt(st.nextToken()); // y번 판매점까지
					// 누적 판매 세트 수량조사
					for (int j = x; j <= y; j++) {
						System.out.print(acc[j] + " ");
						soldSet += acc[j];
					}
					System.out.println();
					System.out.println(soldSet);
				}
			}
			bw.write(soldSet + "\n");
			bw.flush();
		}
		bw.close();
		br.close();
	}
}