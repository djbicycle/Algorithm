package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class Annagram {
	
	static int answer;
	static int sameNumberOfLetters;
	static int unique;
	static HashMap<String, Integer> map;
	static HashMap<String, Integer> cnt; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] word1 = br.readLine().split("");
		String [] word2 = br.readLine().split("");
		int fl = word1.length;
		int sl = word2.length;
		answer = 0;
		sameNumberOfLetters = 0;
		map = new HashMap<String, Integer>();
		cnt = new HashMap<String, Integer>();
		for (int i = 0; i < fl; i++) {
			if(map.containsKey(word1[i])) {
				map.put(word1[i], map.get(word1[i])+1);
				cnt.put(word1[i], 0);
			} else {
				map.put(word1[i], 1);
				cnt.put(word1[i], 0);
			}
		}
		unique = map.size();
		String s;
		String t;
		for (int i = 0; i < sl; i++) {
			s = word2[i];
			t = "";
			System.out.println("이번 문자: "+s);
			if(i >= fl) {
				t = word2[i-fl];
				if(map.containsKey(s)) {
					cnt.put(s, cnt.get(s) + 1);
				}
				if(map.containsKey(t)) {
					cnt.put(t, cnt.get(t) - 1);
				}
			} else {
				if(map.containsKey(s)) {
					cnt.put(s, cnt.get(s) + 1);
				}
			}
			chkNumberOfEachLetter(t, s);
		}
		System.out.println(answer);
		
	}
	
	// 1개씩 들어올 때마다 각 문자의 개수를 확인하기 위해 사용
	public static void chkNumberOfEachLetter(String t, String s) {
		System.out.println("전 문자: "+t+ " : "+map.get(s)+" vs 지금 문자: "+s+" : "+cnt.get(s));
		
		if(map.containsKey(t)) {
			System.out.println("1111111");
			System.out.println("??");				
			if(map.get(t) == cnt.get(t) + 1) {
				System.out.println("22222");
				sameNumberOfLetters--;
			}
		}
		
		if(map.containsKey(s)) {
			System.out.println("33333");
			if(map.get(s) == cnt.get(s)) {
				System.out.println("44444");
				sameNumberOfLetters++;
			} else {
				System.out.println("55555");
				cnt.put(s, cnt.get(s) - 1);
				if(map.get(s) != cnt.get(s)) {
					System.out.println("66666");
					sameNumberOfLetters--;
				}
			}
			if(sameNumberOfLetters < 0) {
				System.out.println("77777");
				sameNumberOfLetters = 0;
			}
			if(unique == sameNumberOfLetters) {
				System.out.println();
				answer++;
				System.out.println("답 나왔다! "+answer);
				System.out.println();
				System.out.println();
				return;
			}			
		}
		for (String ss : cnt.keySet()) {
			System.out.print(ss + " "+ cnt.get(ss)+ " ; ");
		}
		System.out.println();
		System.out.println("동일한 숫자 개수 : "+sameNumberOfLetters);
		System.out.println();
		System.out.println();
	}
	
}
