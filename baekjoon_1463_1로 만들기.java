import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		
		int [] dp = new int[1000001];
		
		//점화식: dp[n] = min(dp[n-1], dp[n/2], dp[n/3) + 1
		dp[1] = 0;
		for(int i=2; i<=N; i++) {
			//1을 빼는 경우
			dp[i] = dp[i-1] + 1;
			
			//2로 나눠 떨어지는 경우
			if(i%2 == 0) 
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			
			//3으로 나눠 떨어지는 경우
			if(i%3 == 0) 
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
		}
		
		//결과 출력
		bw.write(dp[N] + "\n");
		
		bw.close();
	}
}