import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//배열 생성
		int [] A = new int[N];
		int [] B = new int[N];
		
		//입력
		String a = br.readLine();
		StringTokenizer aa = new StringTokenizer(a);
		for(int i=0; i<A.length; i++)
			A[i] = Integer.parseInt(aa.nextToken());
		
		String b = br.readLine();
		StringTokenizer bb = new StringTokenizer(b);
		for(int i=0; i<B.length; i++)
			B[i] = Integer.parseInt(bb.nextToken());
		
		//A 오름차순 정렬
		Arrays.sort(A);
		
		//A[i] * B[j] 중 가장 작은 값 저장
		int ab = 10001; //초기값은 10001(A와 B의 각 원소는 100보다 작거나 같기 때문)
		int idx = 0; //B 배열 방문 표시용 인덱스
		int result = 0; //결과값 저장
		
		//A 배열의 가장 큰 수부터 시작
		for(int i=A.length-1; i>=0; i--) {
			for(int j=0; j<B.length; j++) {
				//B[j]가 -1이라면 이미 방문한 것이므로 continue
				if(B[j] == -1) continue;
				else {
					//ab와 A[i]*B[j]를 비교
					if(ab > A[i] * B[j]) {
						ab = A[i] * B[j];
						idx = j;
					}
				}
			}
			result += ab; //가장 작은 값을 더함
			ab = 10001; //ab 초기화
			B[idx] = -1; //방문 표시
		}
		
		//결과 출력
		bw.write(result + "\n");
		
		bw.close();
	}
}