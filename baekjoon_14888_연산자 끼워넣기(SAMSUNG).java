import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int MAX = Integer.MIN_VALUE; //최대값
	private static int MIN = Integer.MAX_VALUE; //최소값
	
	private static int [] nums; //숫자 배열
	private static int [] oper; //연산자 정보 배열
	private static boolean [] visited; //연산자 배열 방문표시
	
	public static void calc(int [] op) {
		int cur = 0; //더한 결과 저장
		//연산자 순열을 이용해 계산 수행
		for(int i=0; i<op.length; i++) {
			if(i == 0) {
				if(op[i] == 0) cur = (nums[i] + nums[i+1]);
				if(op[i] == 1) cur = (nums[i] - nums[i+1]);
				if(op[i] == 2) cur = (nums[i] * nums[i+1]);
				if(op[i] == 3) {
					if(nums[i] < 0) cur = -(-nums[i] / nums[i+1]);
					else cur = (nums[i] / nums[i+1]);
				}
			}
			else {
				if(op[i] == 0) cur += nums[i+1];
				if(op[i] == 1) cur -= nums[i+1];
				if(op[i] == 2) cur *= nums[i+1];
				if(op[i] == 3) {
					if(cur < 0) cur = -(-cur / nums[i+1]);
					else cur /= nums[i+1];
				}
			}
		}
		
		MAX = Math.max(cur, MAX); //최대값 갱신
		MIN = Math.min(cur, MIN); //최솟값 갱신
	}
	
	//가능한 연산자 순열 찾기
	public static void permu(int depth, int [] op) {
		if(depth == oper.length) { //순열이 완성되면
			calc(op); //결과 계산
			return;
		}
		
		for(int i=0; i<oper.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				op[depth] = oper[i];
				permu(depth+1, op);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//숫자 입력
		nums = new int[N];
		String [] n = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(n[i]);
		}
		
		//연산자 배열(+, -, *, /)
		oper = new int[N-1];
		visited = new boolean[N-1];
		int idx = 0;
		String [] o = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			for(int j=0; j<Integer.parseInt(o[i]); j++) {
				oper[idx++] = i;
			}
		}
		
		//연산자 순열 생성
		int [] op = new int[N-1];
		permu(0, op);
		
		//결과 출력
		System.out.println(MAX + "\n" + MIN + "\n");
		
		bw.close();
	}
}