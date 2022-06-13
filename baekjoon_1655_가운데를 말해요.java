import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		
		//조건1. maxHeap의 peek() < minHeap의 peek()
		//조건2. 만약 조건1 이 성립하지 않는다면 maxHeap의 peek()와 minHeap의 peek()를 swap
		//조건3. maxHeap과 minHeap의 크기는 비슷하게 유지
		int num = 0;
		StringBuffer sb = new StringBuffer(); //시간초과 방지를 위해 StringBuufer 사용
		for(int i=0; i<N; i++) {
			//숫자 입력
			num = Integer.parseInt(br.readLine());
			
			//최대힙과 최소힙의 크기가 같은 경우
			if(maxHeap.size() == minHeap.size()) {
				maxHeap.offer(num); //최대힙에 추가
			}
			//최대힙과 최소힙의 크기가 다른 경우
			else {
				minHeap.offer(num); //최소힙에 추가
			}
			
			//maxHeap의 peek()는 minHeap의 peek()보다 항상 작은 값이어야 함
			//따라서 조건을 만족하지 않는 경우 두 값을 swap
			if(!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(minHeap.poll());
			}
			
			//maxHeap의 peek()가 중간값이 되도록 유지하기 때문에 maxHeap의 peek() 추가
			sb.append(maxHeap.peek() + "\n");
		}
		System.out.println(sb); //결과 출력
	}
}