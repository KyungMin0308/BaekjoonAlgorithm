import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//계수 정렬을 위한 배열 선언
		//입력되는 숫자 크기: 1 ~ 10,000
        	//배열 크기: 10001
		int [] count = new int[10001];
		
		//계수 정렬(카운팅 정렬) 사용
		for(int i=0; i<N; i++) {
			count[Integer.parseInt(br.readLine())]++;
		}
		
		//결과 출력
		for(int i=1; i<count.length; i++) {
            if(count[i] > 0) { //배열 값이 0보다 크면 출력
                for(int j=0; j<count[i]; j++) {
				    bw.write(i + "\n");
			    }   
            }
		}
		
		bw.close();
	}
}
