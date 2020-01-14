package preTest;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Solu_fashion {

	static int T, N, Q;
	static int type, from, to, prdt;
	static long cnt, total;
	static long[][] n; // 원배열
	static long[] t_hat, t_top, t_bottom, l_hat, l_bottom, l_top; // 세그먼트 트
	static long[] node; // 세그먼트 트리 노드 번
	static long[] hat, top, bottom;

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cnt = (long) 0;
		node = new long[10000000];
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 5, 판매점갯수
			Q = Integer.parseInt(st.nextToken()); // 8 , 질의 갯
			total = (long) 0;

			int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1; // 트리 높이
			int t_size = (1 << height) - 1;//
			// System.out.println("t_size"+t_size); //15

			t_hat = new long[t_size];
			t_top = new long[t_size];
			t_bottom = new long[t_size];
			l_hat = new long[t_size];
			l_bottom = new long[t_size];
			l_top = new long[t_size];
			n = new long[N + 1][4]; // 판매점 배열
			hat = new long[N + 1];
			top = new long[N + 1];
			bottom = new long[N + 1];

			for (int j = 0; j < Q; j++) {
				st = new StringTokenizer(br.readLine());
				type = Integer.parseInt(st.nextToken());
				if (type == 1) {
					from = Integer.parseInt(st.nextToken());
					to = Integer.parseInt(st.nextToken());
					prdt = Integer.parseInt(st.nextToken());
					cnt = Long.parseLong(st.nextToken());

					System.out.println("inside??");
					update(1, 1, N, from, to, prdt, cnt);

				} else if (type == 2) {
					from = Integer.parseInt(st.nextToken());
					cnt = Long.parseLong(st.nextToken());

					if (t_hat[from] >= cnt && t_top[from] >= cnt && t_bottom[from] >= cnt) {
						// 각 상품이 cnt 만큼 판매됨.
						update(1, 1, N, from, to, 1, -cnt);
						update(1, 1, N, from, to, 2, -cnt);
						update(1, 1, N, from, to, 3, -cnt);

					} else {
						// 각 상품이 min 만큼 판매됨..
						long min = Math.min(Math.min(t_hat[from], t_top[from]), t_bottom[from]);
						update(1, 1, N, from, to, 1, -min);
						update(1, 1, N, from, to, 1, -min);
						update(1, 1, N, from, to, 1, -min);
					}

				} else if (type == 3) {

					for (int j2 = 0; j2 < t_hat.length; j2++) {
						System.out.println(j2 + "=hat" + t_hat[j2]);
					}
					
					for (int j2 = 0; j2 < t_top.length; j2++) {
						System.out.println(j2 + "=top" + t_top[j2]);
					}
					
					for (int j2 = 0; j2 < t_bottom.length; j2++) {
						System.out.println(j2 + "=bottom" + t_bottom[j2]);
					}

					from = Integer.parseInt(st.nextToken());
					to = Integer.parseInt(st.nextToken());
					total = total + sum(1, 1, N, from, to);

				}
			}
			System.out.println("#" + (i + 1) + " " + total);
		}
	}

	public static void update(int num, int start, int end, int left, int right, int prdt, double cnt) {

		if (prdt == 1) {
			update_lazy(num, start, end, prdt); // 누적된 lazy 없애
			if (right < start || end < left) {
				return;
			}
			if (left <= start && end <= right) {
				t_hat[num] += cnt;
				if (start != end) {
					l_hat[num * 2] += cnt;
					l_hat[num * 2 + 1] += cnt;
				}
				return;
			}
			update(num * 2, start, (start + end) / 2, left, right, prdt, cnt);
			update(num * 2 + 1, (start + end) / 2 + 1, end, left, right, prdt, cnt);
			t_hat[num] = t_hat[num * 2] + t_hat[num * 2 + 1];

		} else if (prdt == 2) {
			update_lazy(num, start, end, prdt); // 누적된 lazy 없애
			if (right < start || end < left) {
				return;
			}
			if (left <= start && end <= right) {
				t_top[num] += cnt;
				if (start != end) {
					l_top[num * 2] += cnt;
					l_top[num * 2 + 1] += cnt;
				}
				return;
			}
			update(num * 2, start, (start + end) / 2, left, right, prdt, cnt);
			update(num * 2 + 1, (start + end) / 2 + 1, end, left, right, prdt, cnt);
			t_top[num] = t_top[num * 2] + t_top[num * 2 + 1];
		} else if (prdt == 3) {
			update_lazy(num, start, end, prdt); // 누적된 lazy 없애
			if (right < start || end < left) {
				return;
			}
			if (left <= start && end <= right) {
				t_bottom[num] += cnt;
				if (start != end) {
					l_bottom[num * 2] += cnt;
					l_bottom[num * 2 + 1] += cnt;
				}
				return;
			}
			update(num * 2, start, (start + end) / 2, left, right, prdt, cnt);
			update(num * 2 + 1, (start + end) / 2 + 1, end, left, right, prdt, cnt);
			t_bottom[num] = t_bottom[num * 2] + t_bottom[num * 2 + 1];
		}

	}

	private static void update_lazy(int num, int start, int end, int prdt) {

		if (prdt == 1) {
			if (l_hat[num] != 0) {
				if (l_hat[num] % 2 == 1) {
					t_hat[num] = (end - start + 1) - t_hat[num];
				}
				if (start != end) {
					l_hat[num * 2] += l_hat[num];
					l_hat[num * 2 + 1] += l_hat[num];
				}
				l_hat[num] = 0;
			}
		} else if (prdt == 2) {
			if (l_top[num] != 0) {
				if (l_top[num] % 2 == 1) {
					t_top[num] = (end - start + 1) - t_top[num];
				}
				if (start != end) {
					l_top[num * 2] += l_top[num];
					l_top[num * 2 + 1] += l_top[num];
				}
				l_top[num] = 0;
			}
		} else if (prdt == 3) {
			if (l_bottom[num] != 0) {
				if (l_bottom[num] % 2 == 1) {
					t_bottom[num] = (end - start + 1) - t_bottom[num];
				}
				if (start != end) {
					l_bottom[num * 2] += l_bottom[num];
					l_bottom[num * 2 + 1] += l_bottom[num];
				}
				l_bottom[num] = 0;
			}
		}
	}

	public static long sum(int num, int start, int end, int left, int right) {
		update_lazy(num, start, end, 1);
		update_lazy(num, start, end, 2);
		update_lazy(num, start, end, 3);

		int result = 0;
		if (right < start || left > end) { // 찾는 범위가 원래 범위의 밖에 있는 경우
			return result;
		}
		if (left <= start && end <= right) { // 찾는 범위가 원래 범위에 속할 경우
			// return node[num];
			return t_hat[num] + t_top[num] + t_bottom[num];
		}
		result += sum(num * 2, start, (start + end) / 2, left, right);
		result += sum(num * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return result;
	}

	private static double initNode(int num, int start, int end) {

		if (start == end) { // 루트노드밖에 없는 경우
			// System.out.println("드루왔어?? ");
			// System.out.println("n[start][0]" + n[start][0]);
			node[num] = n[start][0];
			return node[num];
		} // 아닌 경우 노드를 계속 생성하면서 부모에게는 자식의 합을 저장한다.
			// System.out.println("1");
			// System.out.println("1 " + node[num] + " start " + start + " end " + end);
		node[num] += initNode(num * 2, start, (start + end) / 2);
		// System.out.println("2 " + node[num]);
		node[num] += initNode(num * 2 + 1, (start + end) / 2 + 1, end);
//		System.out.println("node[" + num + "] " + node[num]);
		return node[num];
	}

}