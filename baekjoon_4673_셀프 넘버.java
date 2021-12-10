import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		int [] arr = new int[10001];
		int idx = 0;
		
		for(int i=1; i<arr.length; i++) {
			//arr[i]의 값이 1이라면 이미 계산된 것이므로 넘어감
			if(arr[i] == 1) continue;
			
			//idx가 10000보다 작은 동안 반복해서 셀프넘버를 제외한 수 구하기
			idx = i;
			while(idx <= 10000) {
				//idx가 10보다 작으면
				if(idx < 10) {
					//자기 자신끼리 더함
					idx = idx + idx;
				}
				else {
					//각 자릿수 더하기
					String tmp = String.valueOf(idx);
					int sum = 0;
					for(int j=0; j<tmp.length(); j++) {
						int n = tmp.charAt(j) - '0';
						sum += n;
					}
					//각 자릿수의 합에 원래 값 더하기
					idx = idx + sum;
				}

				//idx가 10000보다 작으면 배열 해당 위치 값을 1로 바꿈
				if(idx <= 10000)
					arr[idx] = 1;
			}
		}
	
		//결과 출력
		for(int i=1; i<arr.length; i++) {
			//arr[i]의 값이 1이 아니라면 셀프 넘버
			if(arr[i] != 1)
				bw.write(i + "\n");
		}

		bw.close();
	}
}