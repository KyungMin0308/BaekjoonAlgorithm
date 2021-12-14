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
		
		//N개만큼 숫자 입력해서 배열에 저장
		int [] nums = new int[N];
		for(int i=0; i<nums.length; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int [] dp = new int[12]; //N은 11보다 작음
		
		//DP
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<dp.length; i++) {
			//점화식: a[i] = a[i-1] + a[i-2] + a[i-3]
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		//결과 출력
		for(int i=0; i<nums.length; i++)
			bw.write(dp[nums[i]] + "\n");
		
		bw.close();
	}
}