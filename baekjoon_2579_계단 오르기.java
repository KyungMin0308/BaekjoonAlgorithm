import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {

		//N(계단수) 입력
		int N = Integer.parseInt(br.readLine());
		
		int [] stairs = new int[N+1]; //계단별 점수 저장, 0번째 인덱스는 사용X
		int [] dp = new int[N+1]; //dp 배열
		
		//각 계단별 점수 입력
		for(int i=1; i<stairs.length; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		/* 점화식 */
		/* dp[n] = Max(stairs[n]+stairs[n-1]+dp[n-3], stairs[n]+dp[n-2]) */
		
		dp[1] = stairs[1]; //첫번째 계단 최대 점수
		
		for(int i=2; i<dp.length; i++) {
			//두번째 게단 최대 점수
			if(i==2) {
				dp[2] = stairs[2]+stairs[1];	
			}
			//세번째 계단 최대 점수
			else if(i==3) {
				dp[3] = Math.max(stairs[3]+stairs[1], stairs[3]+stairs[2]);		
			}
			//네번째 계단 이후
			else {
				dp[i] = Math.max(stairs[i]+stairs[i-1]+dp[i-3], stairs[i]+dp[i-2]);
			}
		}
		
		//결과 출력
		bw.write(dp[N] + "\n");
		
		bw.close();
	}
}