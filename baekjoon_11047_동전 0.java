import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		//N과 K 입력
		String nk = br.readLine();
		StringTokenizer st = new StringTokenizer(nk);
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//N 길이만큼 동전 배열 생성
		int [] coins = new int[N];
		
		//동전 가치 입력
		for(int i=0; i<coins.length; i++)
			coins[i] = Integer.parseInt(br.readLine());
		
		//필요한 동전 최소 개수 계산
		int count = 0; //필요한 동전 개수
		for(int i=coins.length-1; i>=0; i--) {
			count += (K/coins[i]);
			K %= coins[i];
		}
		
		bw.write(count + "\n");
		
		bw.close();
	}
}