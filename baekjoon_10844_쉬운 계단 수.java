import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		//MOD
		long MOD = 1000000000;
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//dp 배열
		long [][] dp = new long[N+1][10];
		
		//dp 배열 초기화
		//한자리 계단 수
		dp[1][0] = 0;
		for(int i=1; i<10; i++) {
			dp[1][i] = 1;
		}
		
		//dp 수행
		//두자리 이상의 계단 수
		for(int i=2; i<N+1; i++) {
			for(int j=0; j<10; j++) {
				if(j == 0) dp[i][j] = dp[i-1][j+1] % MOD; //끝자리가 0일 땐 이전 단계의 끝자리 1인 경우의 수와 동일
				else if(j == 9) dp[i][j] = dp[i-1][j-1] % MOD; //끝자리가 9일 땐 이전 단계의 끝자리 8인 경우의 수와 동일
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD; //나머지는 이전 단계의 끝자리 +1, -1인 경우의 수를 더함
			}
		}
		
		//정답 도출
		long ans = 0;
		for(int i=0; i<10; i++) {
			ans = (ans + dp[N][i]) % MOD;
		}
		
		bw.write(ans + "\n");
		
		bw.close();
	}
}