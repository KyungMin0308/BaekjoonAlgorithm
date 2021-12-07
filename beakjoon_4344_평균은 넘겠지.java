import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//평균 구하는 함수
	private static double getAver(int [] arr) {
		int sum = 0;
		for(int i=0; i<arr.length; i++)
			sum += arr[i];
		
		return (double) sum/arr.length;
	}
	
	//평균 넘는 학생 수 구하는 함수
	private static double getRatio(int [] arr, double aver) {
		double ratio = 0.0;
		int count = 0;
		for(int i=0; i<arr.length; i++) //평균 넘는 학생 수 카운트
			if(arr[i] > aver)
				count++;
		
		//비율 계산
		ratio = ((double) count/arr.length) * 100;
		
		return ratio;
	}
	
	public static void main(String[] args) throws IOException{
		
		//테스트케이스 개수 입력
		int C = Integer.parseInt(br.readLine());
		
		//테스트케이스 개수만큼 반복
		for(int i=0; i<C; i++) {
			//학생수(N), 점수 입력
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int N = Integer.parseInt(st.nextToken()); //학생 수
			int [] scores = new int[N]; //학생 수만큼 점수 배열 생성
			
			//배열에 점수 저장
			for(int j=0; j<N; j++)
				scores[j] = Integer.parseInt(st.nextToken());
			
			double aver = getAver(scores); //평균 계산
			double ratio = getRatio(scores, aver); //평균을 넘는 학생 비율 계산
			
			//결과 출력
			//소수점 자리 세번째까지 출력
			bw.write(String.format("%.3f", ratio) + "%\n");
		}
		
		bw.close();
	}
}