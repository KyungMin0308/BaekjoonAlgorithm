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
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		int [] rank = new int[N]; //랭킹 저장 배열
		int [][] wh = new int[N][2]; //몸무게, 키 저장 배열
		
		//몸무게, 키 입력
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			wh[i][0] = Integer.parseInt(st.nextToken());
			wh[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//순위 매기기
		for(int i=0; i<N; i++) {
			int count = 0; //자신보다 큰 덩치 사람 수 카운트
			int curWeight = wh[i][0]; //자신의 몸무게
			int curHeight = wh[i][1]; //자신의 키
			
			for(int j=0; j<N; j++) {
				if(i == j) continue; //i와 j가 같다면 본인이므로 continue
				//자신보다 키와 몸무게가 모두 큰 사람 수 카운트
				if(curWeight < wh[j][0] && curHeight < wh[j][1])
					count++;
			}
			
			if(count == 0) //count가 0이면 자신보다 덩치가 큰 사람이 없음
				rank[i] = 1;
			else //count가 0이 아니라면 count+1이 자신의 등수
				rank[i] = count+1;
		}
		
		//결과 출력
		for(int i=0; i<rank.length-1; i++)
			bw.write(rank[i] + " ");
		bw.write(rank[N-1] + "\n");
		
		bw.close();
	}
}