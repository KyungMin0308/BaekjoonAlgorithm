import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		
		//N, M
		String [] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		//DNA 목록 입력
		String [] dnas = new String[N];
		for(int i=0; i<N; i++) {
			dnas[i] = br.readLine();
		}
		
		//A(0), T(1), G(2), C(3)
		ArrayList<Integer> lst = new ArrayList<Integer>();
		String dna = ""; //Humming Distance가 최소인 DNA

		//Humming Distance가 최소인 DNA 구하기
		for(int i=0; i<M; i++) {
			//리스트 초기화
			for(int m=0; m<4; m++) {
				lst.add(0);
			}
			
			//뉴클레오티드 문자별 등장 횟수 구하기
			for(int j=0; j<N; j++) {
				if(dnas[j].charAt(i) == 'A') {
					lst.set(0, lst.get(0)+1);
				}
				else if(dnas[j].charAt(i) == 'T') {
					lst.set(1, lst.get(1)+1);
				}
				else if(dnas[j].charAt(i) == 'G') {
					lst.set(2, lst.get(2)+1);
				}
				else if(dnas[j].charAt(i) == 'C') {
					lst.set(3, lst.get(3)+1);
				}
			}
			
			//가장 많이 나오는 뉴클레오티드 문자 찾기
			int max = Collections.max(lst);	
			ArrayList<Character> chs = new ArrayList<Character>();
			for(int k=0; k<lst.size(); k++) {
				if(max == lst.get(k)) {
					if(k == 0) chs.add('A');
					else if(k == 1) chs.add('T');
					else if(k == 2) chs.add('G');
					else if(k == 3) chs.add('C');
				}
			}
			
			//가장 많이 나오는 뉴클레오티드가 여러 개라면
			//사전 순 정렬 후 맨 앞에 것 선택
			Collections.sort(chs);
			dna += chs.get(0);
			
			//리스트 비우기
			lst.clear();
			chs.clear();
		}
		
		//생성된 DNA와 dnas의 Humming Distance 구하기
		int hd = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//같은 위치의 문자가 다르면 증가
				if(dna.charAt(j) != dnas[i].charAt(j)) {
					hd++;
				}
			}
		}
		
		//결과 출력
		bw.write(dna + "\n" + hd + "\n");
		
		bw.close();
	}
}