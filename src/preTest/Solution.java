package preTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<double []> list1; 
	static ArrayList<double []> list2; 
	static ArrayList<double []> list3; 
	static ArrayList<double []> list4; 
	static int [] ans;
	static int order;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int N = 0; 
		int L = 0; 
		int H = 0; 
		int x = 0; 
		int y = 0;
		double z = 0;
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			ans = new int [N+1];
			list1 = new ArrayList<double[]>(); 
			list2 = new ArrayList<double[]>(); 
			list3 = new ArrayList<double[]>(); 
			list4 = new ArrayList<double[]>(); 

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
				double [] temp = new double [2];
				if(z >= H && z <= L) {
					if(x>0 && y<=0) {
						divideTolist(1, i, x, y, temp);						
					} else if (x<=0 && y<0) {
						divideTolist(2, i, x, y, temp);
					} else if (x<0 && y>=0) {
						divideTolist(3, i, x, y, temp);
					} else {
						divideTolist(4, i, x, y, temp);
					}
				} else {
					ans[i] = 0;
				}
			}
			
			sortList(list1, -1);
			sortList(list2, 1);
			sortList(list3, -1);
			sortList(list4, 1);
			
			order = 1;
			cnt = 0;
			
			double tilt = 0;
			tilt = putListToAns(list1, tilt);
			tilt = putListToAns(list2, tilt);
			tilt = putListToAns(list3, tilt);
			putListToAns(list4, tilt);
			
			System.out.print("#"+t);
			for (int i = 1; i <= N; i++) {
				System.out.print(" "+ans[i]);
			}
			System.out.println();
			
		}
	}
	
	static double putListToAns(ArrayList<double []> list, double tilt) {
//		System.out.println("XX");
		for (int i = 0; i < list.size(); i++) {
			cnt++;
//			System.out.println("cnt "+cnt+" order "+order);
			if(tilt != list.get(i)[1]) {
//				System.out.println("다");
				order = cnt;
				ans[(int) list.get(i)[0]] = cnt;
			} else {
//				System.out.println("같");
				ans[(int) list.get(i)[0]] = order++;
			}
//			System.out.println();
			tilt = list.get(i)[1];
		}
		return tilt;
	}
	
	static void sortList(ArrayList<double[]> list, int sortNumUp) {
		Collections.sort(list, new Comparator<double []>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				if(o1[1] > o2[1]) {
					return 1 * sortNumUp;
				} else if (o1[1] < o2[1]) {
					return -1 * sortNumUp;
				} else {
					return 0;
				}
			}
		});
	}
	static void divideTolist(int n, int i, int x, int y, double [] input) {
		input[0] = i;
		if(x==0&& y<0) {
			input[1] = -20001;
		} else if (x==0&& y>0) {
			input[1] = 20001;
		} else {
			input[1] = y/x;
		}
		if(n==1) {
			list1.add(input);
		} else if (n==2) {
			list2.add(input);
		} else if (n==3) {
			list3.add(input);
		} else {
			list4.add(input);
		}
	}
}
