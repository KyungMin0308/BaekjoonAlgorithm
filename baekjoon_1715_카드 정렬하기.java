import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static PriorityQueue<Long> pq = new PriorityQueue<Long>(); //카드 묶음 저장 우선순위큐
	static int N = 0; //숫자 카드 묶음 개수
	static long ans = 0; //정답
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		N = Integer.parseInt(br.readLine());
		
		//카드 묶음 입력
		for(int i=0; i<N; i++) {
			pq.offer(Long.parseLong(br.readLine()));
		}
		
		//정답 계산
		if(N == 1) { //N이 1인 경우 비교할 필요 없음
			ans = 0;
		}
		else { //N이 1보다 크면
			//큐의 크기가 1일 때까지 반복
			while(pq.size() > 1) {
				//가장 작은 크기의 묶음 2개 선택
				long c1 = pq.poll();
				long c2 = pq.poll();
				
				//비교
				long group = c1 + c2;
				ans += group; //정답 갱신
				
				//큐에 새로운 데이터 추가
				pq.offer(group);
			}
		}
		
		//정답 출력
		bw.write(ans + "\n");
		
		bw.close();
	}
}