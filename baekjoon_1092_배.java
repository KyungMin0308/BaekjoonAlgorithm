import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N; //크레인 수
	static int M; //박스 수
	
	static Integer [] crane; //크레인
	static List<Integer> box = new ArrayList<Integer>(); //박스 리스트
	
	static int TIME = 0; //시간

	public static void main(String[] args) throws IOException {
	
		N = Integer.parseInt(br.readLine());
		
		crane = new Integer[N];

		//크레인 무게 제한 정보 입력
		String [] cr = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			crane[i] = Integer.parseInt(cr[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		//박스 무게 입력
		String [] bx = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			box.add(Integer.parseInt(bx[i]));
		}
		
		Arrays.sort(crane, (x, y) -> y-x); //크레인 무게제한 내림차순 정렬
		Collections.sort(box, Collections.reverseOrder()); //박스 무게 내림차순 정렬
		
		//박스의 최고 무게가 크레인 무게제한의 최고 무게보다 크면 모든 박스를 옮길 수 없음
		if(crane[0] < box.get(0)) {
			System.out.println(-1);
		}
		else {
			//박스 리스트가 빌때까지 반복
			while(!box.isEmpty()) {
				int idx = 0; //박스 리스트 인덱스
				
				for(int i=0; i<crane.length; i++) {
					if(idx == box.size()) break; //인덱스가 리스트 크기와 같아지면 break
					
					if(crane[i] >= box.get(idx)) { //크레인으로 박스를 옮길 수 있다면
						box.remove(idx); //박스를 옮김
					}
					else { //옮길 수 없다면
						idx++; //다음 박스 확인
						i--; //크레인은 그대로
					}
				}
				TIME++; //시간 경과
			}
			
			//결과 출력
			System.out.println(TIME);
		}		
	}
}