import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N; //학생 수
	static int M; //비교 횟수
	
	static ArrayList<ArrayList<Integer>> students = new ArrayList<ArrayList<Integer>>(); //학생 비교 정보 그래프
	static int [] enter; //노드별 진입 차수 배열
	
	//줄 세우기(위상정렬)
	public static void makeLine() {
		Queue<Integer> queue = new LinkedList<Integer>(); //위상정렬을 위한 큐
		
		//진입 차수가 0인 학생을 큐에 추가
		for(int i=1; i<=N; i++) {
			if(enter[i] == 0) queue.offer(i);
		}
		
		//큐가 빌때까지 정렬 수행
		while(!queue.isEmpty()) {
			int cur = queue.poll(); //현재 학생 번호
			System.out.print(cur + " "); //결과 출력(큐에 들어온 순서가 정렬 결과)
			
			//현재 학생보다 뒤에 있어야 하는 학생 파악
			for(int i=0; i<students.get(cur).size(); i++) {
				int next = students.get(cur).get(i); //뒤에 있어야 하는 학생 번호
				
				if(enter[next] != 0) {
					enter[next]--; //해당 학생의 진입차수 1감소
				}
				
				if(enter[next] == 0) {
					queue.offer(next); //진입 차수가 0이 되었다면 큐에 추가
				}
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		
		//N, M 입력
		String [] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		enter = new int[N+1]; //진입차수 배열 초기화
		for(int i=0; i<=N; i++) students.add(new ArrayList<Integer>()); //그래프 초기화
		
		//비교 정보 입력
		int st, end;
		for(int i=0; i<M; i++) {
			String [] m = br.readLine().split(" ");
			st = Integer.parseInt(m[0]);
			end = Integer.parseInt(m[1]);
			
			students.get(st).add(end);
			enter[end]++; //진입차수 카운트
		}
		
		//줄 세우기
		makeLine();
		
	}
}