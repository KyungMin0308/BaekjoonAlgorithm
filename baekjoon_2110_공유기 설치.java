import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//공유기 설치 함수
	public static boolean install(int [] houses, int c, int mid) {
		int start = houses[0]; //0번째 집에 공유기 설치
		int count = 1; //0번째 집에 공유기를 설치했기 때문에 초기값은 1
		
		for(int i=1; i<houses.length; i++) {
			if(houses[i] - start >= mid) { //설치 간격보다 크면 설치 가능
				count++;
				start = houses[i]; //설치한 위치로 갱신
				
				if(count >= c) return true; //C개 이상이면 true 리턴
			}
		}
		return false;
	}
	
	public static void main(String [] args) throws IOException {
		
		//N, C 입력
		String [] nc = br.readLine().split(" ");
		int N = Integer.parseInt(nc[0]);
		int C = Integer.parseInt(nc[1]);
		
		//집 좌표 입력
		int [] houses = new int[N];
		for(int i=0; i<N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses); //오름차순 정렬
		
		int distance = 0; //공유기 사이 거리
		int min = 1; //공유기 사이 거리 최소값
		int max = houses[N-1] - houses[0]; //공유기 사이 거리 최대값
		
		//이분 탐색
		while(min <= max) {
			int mid = (min + max) / 2; //공유기 설치 거리
			
			if(install(houses, C, mid)) { //C개의 공유기를 설치할 수 있다면
				min = mid + 1;
				distance = mid;
			}
			else { //C개의 공유기 설치할 수 없음
				max = mid - 1;
			}
		}
		
		//결과 출력
		System.out.println(distance);
	}
}