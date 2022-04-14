import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//문자열로 덱 생성
	public static Deque<Integer> make(String str) {
		StringTokenizer st = new StringTokenizer(str, "[],");
		Deque<Integer> arr = new LinkedList<Integer>();
		while(st.hasMoreTokens()) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		return arr;
	}
	
	//정답 문자열 생성(String을 사용하면 시간 초과)
	public static StringBuilder ans(Deque<Integer> arr, boolean rev) {
		StringBuilder sb = new StringBuilder();
		
		if(arr.size() == 0) return sb.append("[]"); //함수 수행 후 리스트 크기가 0이면
		
		//덱 크기가 1 이상인 경우
		int size = arr.size()-1;
		sb.append("[");
		for(int i=0; i<size; i++) {
			if(rev) sb.append((String.valueOf(arr.pollLast()) + ",")); //뒤집은 상태로 뒤부터 poll
			else sb.append((String.valueOf(arr.pollFirst()) + ",")); //뒤집지 않은 상태로 앞부터 poll
		}
		sb.append(String.valueOf(arr.poll()) + "]");
		
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			String p = br.readLine(); //수행할 함수
			int n = Integer.parseInt(br.readLine()); //배열에 들어있는 수
			String arr = br.readLine(); //배열 문자열 입력
			
			Deque<Integer> nums = make(arr); //배열 생성
			boolean err = false; //에러 발생 여부
			boolean rev = false; //뒤집기 여부(뒤집지 않은 상태)
			
			//수행할 함수만큼 반복
			for(int j=0; j<p.length(); j++) {
				if(p.charAt(j) == 'R') { //뒤집기
					if(!rev) rev = true; //뒤집은 상태
					else rev = false;
				}
				else if(p.charAt(j) == 'D') { //첫번째 숫자 제거
					if(nums.size() == 0) {
						err = true;
						bw.write("error\n");
						break;
					}
					else {
						if(rev) nums.pollLast(); //문자열을 뒤집었으므로 뒤부터 제거
						else nums.pollFirst(); //문자열을 뒤집지 않았으므로 앞부터 제거
					}
				}
			}
			
			//에러가 발생하지 않았다면 정답 문자열 생성
			if(!err) bw.write(ans(nums, rev) + "\n");
		}
		
		bw.close();
	}
}