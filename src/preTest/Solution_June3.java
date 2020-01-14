package preTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution_June3 {
	static coordinate[] co;
	static int N;
	static ArrayList<Integer> quadrant2;
	static ArrayList<Integer> quadrant3;
	static ArrayList<Integer> quadrant4;
	static boolean show;
	static int remain;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader out = new BufferedReader(new FileReader("/Users/bongkeunshin/Downloads/output.txt"));
//		ArrayList<int[]> outal = new ArrayList<int[]>();
//		for (int i = 0; i < 10; i++) {
//			String [] sss = out.readLine().split(" ");
//			int [] iii = new int [sss.length];
//			for (int j = 0; j < iii.length; j++) {
//				iii[j] = Integer.parseInt(sss[j]);
//			}
//			outal.add(iii);
//		}
//		BufferedReader br = new BufferedReader(new FileReader("D://sample_input.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("/Users/bongkeunshin/Downloads/sample_input.txt"));
		
		int Test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= Test; t++) {
			N = Integer.parseInt(br.readLine());
			co = new coordinate[N];
			String[] s = new String[4];
			quadrant2 = new ArrayList<>();
			quadrant3 = new ArrayList<>();
			quadrant4 = new ArrayList<>();
			show = false;
			remain = N;
			long x1 = 0;
			long x2 = 0;
			long y1 = 0;
			long y2 = 0;
			for (int i = 0; i < N; i++) {
				s = br.readLine().split(" ");
				x1 = Long.parseLong(s[0]);
				y1 = Long.parseLong(s[1]);
				x2 = Long.parseLong(s[2]);
				y2 = Long.parseLong(s[3]);
				co[i] = new coordinate(x1, y1, x2, y2);
				if (x1 < 0 && y1 > 0) {
					quadrant2.add(i);
				} else if (x1 < 0 && y1 < 0) {
					quadrant3.add(i);
				} else if (x1 > 0 && y1 < 0){
					quadrant4.add(i);
				}
				if (x2 < 0 && y2 > 0) {
					quadrant2.add(101 + i);
				} else if (x2 < 0 && y2 < 0) {
					quadrant3.add(101 + i);
				} else if (x2 > 0 && y2 < 0){
					quadrant4.add(101 + i);
				}
			}

			for (int i = 0; i < 32; i++) {
				if(remain <= 1 ) break;
				calculate(i);
				if (i % 10 == 9) {
					mapShrink((i + 1) / 10);
				}
			}
			System.out.print("#" + t);
			for (int i = 0; i < N; i++) {
				System.out.print(" " + co[i].getGameOverTime());
			}
			System.out.println();
			
			
////			//////////
//			System.out.print("*" + t);
////			////
////			t = 6;
////			////			
//			for (int i = 0; i < N; i++) {
//				System.out.print(" " + outal.get(t-1)[i]);
//			}
//			System.out.println();
//			System.out.print("=" + t);
//			for (int i = 0; i < N; i++) {
//				int a;
//				if(outal.get(t-1)[i]<10) {
//					a = 1;
//				} else {
//					a = 2;
//				}
//				for (int j = 0; j < a; j++) {
//					System.out.print(" ");
//				}
//				if(co[i].getGameOverTime() == outal.get(t-1)[i]) {
//					System.out.print("O");
//				} else {
//					System.out.print("X");
//				}
//			}
//			System.out.println();
//			System.out.println();
		}
	}

	public static void mapShrink(int n) {
		int dot = 0;
		if (n == 1) { // 4->3 사분면 이동
			for (int i = 0; i < quadrant4.size(); i++) {
				dot = quadrant4.get(i);
				if (dot >= 101) {
					co[dot - 101].setX2(co[dot - 101].getX2() * -1);
				} else {
					co[dot].setX1(co[dot].getX1() * -1);
				}
				quadrant3.add(dot);
			}
//			System.out.println("4->3 사분면 이동");
//			for (int i = 0; i < quadrant3.size(); i++) {
//				System.out.print(quadrant3.get(i)+" ");
//			}
		} else if (n == 2) { // 3->2 사분면 이동
			for (int i = 0; i < quadrant3.size(); i++) {
				dot = quadrant3.get(i);
				if (dot >= 101) {
					co[dot - 101].setY2(co[dot - 101].getY2() * -1);
				} else {
					co[dot].setY1(co[dot].getY1() * -1);
				}
				quadrant2.add(dot);
			}
//			System.out.println("3->2 사분면 이동");
//			for (int i = 0; i < quadrant2.size(); i++) {
//				System.out.print(quadrant2.get(i)+" ");
//			}
		} else { // 2->1 사분면 이동
			for (int i = 0; i < quadrant2.size(); i++) {
				dot = quadrant2.get(i);
				if (dot >= 101) {
					co[dot - 101].setX2(co[dot - 101].getX2() * -1);
				} else {
					co[dot].setX1(co[dot].getX1() * -1);
				}
			}
		}
	}
	public static void calculate(int loop) {
		System.out.println("=========" + loop + "번째 loop =========");
		long ix1 = 0;
		long iy1 = 0;
		long ix2 = 0;
		long iy2 = 0;
		long jx1 = 0;
		long jy1 = 0;
		long jx2 = 0;
		long jy2 = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N - 1; i++) {
//			System.out.println();
			//i인풋
			ix1 = co[i].getX1();
			iy1 = co[i].getY1();
			ix2 = co[i].getX2();
			iy2 = co[i].getY2();
//			System.out.println("i : "+ i +" 시작 ( " + ix1 + " " + iy1 + " ) , (" + ix2 + " " + iy2 + " )"
//					+ " GameOverTime = " + co[i].getGameOverTime());
			//이미 끝 (32보다 작은 수)
			if (co[i].getGameOverTime() < 32) {
//				System.out.println("i = "+i+" , deadtime: "+co[i].getGameOverTime()+" , continue");
				continue;
			}
			
			for (int j = i + 1; j < N; j++) {
				//j인풋
				jx1 = co[j].getX1();
				jy1 = co[j].getY1();
				jx2 = co[j].getX2();
				jy2 = co[j].getY2();
//				if((i==3||i==2)&&j==10) {//&&(j==33||j==32)
//					show = true;
//					System.out.println("i :"+ i +" 비교 ( " + ix1 + " " + iy1 + " ) , (" + ix2 + " " + iy2 + " )");	
//					System.out.println("j :"+ j +" 비교 ( " + jx1 + " " + jy1 + " ) , (" + jx2 + " " + jy2 + " )");
//				} else {
//					show = false;
//				}
//				///
//				show = true;
				System.out.println("i :"+ i +" 비교 ( " + ix1 + " " + iy1 + " ) , (" + ix2 + " " + iy2 + " )");	
				System.out.println("j :"+ j +" 비교 ( " + jx1 + " " + jy1 + " ) , (" + jx2 + " " + jy2 + " )");
				///
				if (co[j].getGameOverTime() < 32) {
//					System.out.println("j = "+j+" , deadtime: "+co[j].getGameOverTime()+" , continue");
					continue;
				}
//				System.out.println(ix1 + " " + iy1 + " " + ix2 + " " + iy2 + " " + jx1 + " " + jy1 + " " + jx2 + " " + jy2);
				//동일한 점위에 있는 경우				
				if (ix1 == jx1 && iy1 == jy1 || ix1 == jx2 && iy1 == jy2
						|| ix2 == jx1 && iy2 == jy1 || ix2 == jx2 && iy2 == jy2) {
					//전투 조건 만족, 게임오버
					set.add(i);
					set.add(j);
				// 각각의 선분을 기준으로 CCW 끼리 곱해서 마이너스, 0 이면 겹치거나, 평행선상에 있음   
				} else if (CCW(ix1, iy1, ix2, iy2, jx1, jy1) * CCW(ix1, iy1, ix2, iy2, jx2, jy2) <= 0
						&& CCW(jx1, jy1, jx2, jy2, ix1, iy1) * CCW(jx1, jy1, jx2, jy2, ix2, iy2) <= 0) {
					//두 선분이 평행선상에 존재하는지 확인
					if (CCW(ix1, iy1, ix2, iy2, jx1, jy1) * CCW(ix1, iy1, ix2, iy2, jx2, jy2) == 0
							&& CCW(jx1, jy1, jx2, jy2, ix1, iy1) * CCW(jx1, jy1, jx2, jy2, ix2, iy2) == 0) {
						//두 선분이 평행선상에 존재
						//평행선 상에 존재하는 두 선분이 겹치는지 확인
						boolean fight = false;
						// y축에 평행한 경우
						if(ix2-ix1 == 0 && jx2-jx1 == 0 && ix2 - jx1 == 0 && ix2 - jx2 == 0 && jx1 - ix1 == 0 && ix1 - jx2 == 0) {
							// 함수 결과-> true: 만남(전투), false: 만나지 않음(전투X)
							if(chkCross(iy1, iy2, jy1) || chkCross(iy1, iy2, jy2)) {
//								System.out.println("1");
								fight = true;
							}
						// x축에 평행한 경우
						} else if (iy2-iy1 == 0 && jy2-jy1 == 0 && iy2 - jy1 == 0 && iy2 - jy2 == 0 && jy1 - iy1 == 0 && iy1 - jy2 == 0) {
							// 함수 결과-> true: 만남(전투), false: 만나지 않음(전투X)
							if(chkCross(ix1, ix2, jx1) || chkCross(ix1, ix2, jx2)) {
//								System.out.println("2");
								fight = true;
							}
						// 기울기가 있는 평행
						} else {
							if(chkCross(iy1, iy2, jy1) || chkCross(iy1, iy2, jy2)
									|| chkCross(ix1, ix2, jx1) || chkCross(ix1, ix2, jx2)) {
//								System.out.println("3");
								fight = true;
							}
						}
						if (fight){ //전투 조건 만족, 게임오버
//							System.out.println("트루, 들어가는 답"+loop + " i: "+i+" j: "+j);
							set.add(i);
							set.add(j);
						}
					} else { //전투 조건 만족, 게임오버 
						// 두 선분이 평행선상이 아님 
//						System.out.println("엘스트루, 들어가는 답"+loop + " i: "+i+" j: "+j);
						if(CCW(ix1, iy1, ix2, iy2, jx1, jy1) * CCW(ix1, iy1, ix2, iy2, jx2, jy2) <= 0
						&& CCW(jx1, jy1, jx2, jy2, ix1, iy1) * CCW(jx1, jy1, jx2, jy2, ix2, iy2) > 0) {
							
						} else if (CCW(ix1, iy1, ix2, iy2, jx1, jy1) * CCW(ix1, iy1, ix2, iy2, jx2, jy2) > 0
								&& CCW(jx1, jy1, jx2, jy2, ix1, iy1) * CCW(jx1, jy1, jx2, jy2, ix2, iy2) <= 0) {
							
						} else if(CCW(ix1, iy1, ix2, iy2, jx1, jy1) * CCW(ix1, iy1, ix2, iy2, jx2, jy2) > 0
						&& CCW(jx1, jy1, jx2, jy2, ix1, iy1) * CCW(jx1, jy1, jx2, jy2, ix2, iy2) > 0) {
							
						}
						set.add(i);
						set.add(j);
					}
				}
			}
		}
		for (Integer k : set){
			co[k].setGameOverTime(loop);
			remain--;
		}
		
		for (int i = 0; i < N; i++) {
			co[i].half();
		}
	}
	public static boolean chkCross(long a, long b, long c) {
		// a, b는 같은 선분 위 점, c는 다른 선분 위 점
		long min = 0;
		long max = 0;
		
		if( b == c || c == a) {
			return true;
		} else if(a < b) {
			min = a;
			max = b;
		} else {
			min = b;
			max = a;
		}
		// true: 만남(전투), false: 만나지 않음(전투X)
//		System.out.println("min "+ min+" max "+max+" c "+c);
		if(min<=c && c<=max) {
//			System.out.println("true");
			return true;
		} else {
			return false;
		}
	}
	public static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
//		if(show)System.out.println("x1 : "+x1+", y1 : "+y1+", x2 : "+x2+", y2 : "+y2+", x3 : "+x3+", y3 : "+y3);
		long ans = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
//		if(show)System.out.println("ans = "+ans +" ,"+ (x2 - x1) +" * " + (y3 - y1) +" - "+ (y2 - y1) +" * "+ (x3 - x1));
		if (ans == 0) {
			return 0;
		} else if (ans > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}