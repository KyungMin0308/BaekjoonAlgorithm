import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		int [] time = new int[N]; //상담시간 배열
		int [] price = new int[N]; //상담금액 배열
		
        //상담시간, 상담금액 입력
		for(int i=0; i<N; i++) {
			String [] tp = br.readLine().split(" ");
			time[i] = Integer.parseInt(tp[0]);
			price[i] = Integer.parseInt(tp[1]);
		}
		
		//DP
		int [] dp = new int[N+1];
		
		for(int i=0; i<N; i++) {
			//현재 상담일에 상담시간을 더한 값이 N일 이하라면 상담이 가능함
			if(i + time[i] <= N) {
				//현재 상담일에 상담을 진행했다면 상담금액을 더하고 dp 배열 갱신
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + price[i]);
			}
			//현재 상담일에 상담을 하지 못하더라도 상담 금액은 유지되야함
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		//결과 출력
		bw.write(dp[N] + "\n");
		
		bw.close();
	}
}