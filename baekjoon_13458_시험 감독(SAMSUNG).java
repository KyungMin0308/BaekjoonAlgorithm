import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		//N: 시험장 개수
		int N = Integer.parseInt(br.readLine());
		int [] exam = new int[N]; //시험장 배열
		
		//A: 각 시험장에 있는 응시자 수
		String [] nums = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			exam[i] = Integer.parseInt(nums[i]);
		}
		
		//B: 총시험관이 감시할 응시자 수
		//C: 부감독관이 감시할 응시자 수
		String [] bc = br.readLine().split(" ");
		int B = Integer.parseInt(bc[0]);
		int C = Integer.parseInt(bc[1]);
		
		//필요한 감독관 최소 수
		long min = 0;
		
		for(int i=0; i<exam.length; i++) {
			exam[i] -= B; //총감독관 감시할 응시자 수 제외
			min += 1; //총감독관 1명
			
            //남은 응시자 수가 0명보다 많으면
			if(exam[i] > 0) {
				min += (exam[i] / C); //필요한 부감독관 수
				if(exam[i] % C != 0) min += 1; //나눠떨어지지 않으면 부감독관 1명 더 필요
			}
		}
		
		bw.write(min + "\n");
		
		bw.close();
	}
}