import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N = 0; //용액의 수
	static int [] sol; //용액 배열
	
	static int [] res = new int[2]; //정답 배열
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		sol = new int[N];
		
        //용액 데이터 입력
		String [] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) sol[i] = Integer.parseInt(s[i]);
		
		Arrays.sort(sol); //오름차순 정렬
		
		int mixed = Integer.MAX_VALUE; //섞은 용액의 합
		int st = 0; //시작 인덱스
		int end = N-1; //마지막 인덱스
		
		while(st < end) {
			int curMixed = sol[st] + sol[end]; //현재 st, end 용액을 섞은 값
			
			//현재 섞은 용액의 합이 기존에 섞은 용액의 합보다 작거나 같다면
			if(Math.abs(curMixed) <= Math.abs(mixed)) {
				mixed = curMixed; //기존값 갱신
				//정답 배열에 값 저장
				res[0] = sol[st];
				res[1] = sol[end];
			}
			
			if(curMixed < 0) { //현재 st, end 용액의 합이 0보다 작으면 st 증가
				//작은 값이 커져야 용액의 합이 0에 가까워지기 때문
				st++;
			}
			else { //현재 st, end 용액의 합이 0보다 크면 end 증가
				//큰 값이 작아져야 용액의 합이 0에 가까워지기 때문
				end--;
			}
		}
		
		//정답 출력
		//Arrays.sort(res);
		System.out.println(res[0] + " " + res[1]);
	}
}