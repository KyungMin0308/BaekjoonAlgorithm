import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N, M;
	private static int [][] map;
	
	private static int MAX = Integer.MIN_VALUE;
	
	//막대 모양(총 2개의 모양이 존재)
	public static void shape1() {
		int size = 0;
		for(int i=0; i<N; i++) { //1번.
			for(int j=0; j<M-3; j++) {
				size = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-3; i++) { //2번.
			for(int j=0; j<M; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
				MAX = Math.max(MAX, size);
			}
		}
	}
	
	//'ㅁ' 모양(총 1개의 모양이 존재)
	public static void shape2() {
		int size = 0;
		for(int i=0; i<N-1; i++) { //1번.
			for(int j=0; j<M-1; j++) {
				size = map[i][j] + map[i+1][j] + map[i][j+1] + map[i+1][j+1];
				MAX = Math.max(MAX, size);
			}
		}
	}
	
	//'ㄴ' 모양(총 8개의 모양이 존재)
	public static void shape3() {
		int size = 0;
		for(int i=0; i<N-2; i++) { //1번.
			for(int j=0; j<M-1; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=1; i<N; i++) { //2번.
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+2];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //3번.
			for(int j=1; j<M; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i][j-1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-1; i++) { //4번. 
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i+1][j] + map[i][j+1] + map[i][j+2];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //5번. 
			for(int j=1; j<M; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j-1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-1; i++) { //6번. 
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //7번. 
			for(int j=0; j<M-1; j++) {
				size = map[i][j] + map[i][j+1] + map[i+1][j] + map[i+2][j];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-1; i++) { //8번. 
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+2];
				MAX = Math.max(MAX, size);
			}
		}
	}
	
	//계단 모양(총 4개의 모양이 존재)
	public static void shape4() {
		int size = 0;
		for(int i=0; i<N-2; i++) { //1번. 
			for(int j=0; j<M-1; j++) {
				size = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=1; i<N; i++) { //2번. 
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i-1][j+2];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //3번. 
			for(int j=1; j<M; j++) {
				size = map[i][j] + map[i+1][j] + map[i+1][j-1] + map[i+2][j-1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-1; i++) { //4번. 
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2];
				MAX = Math.max(MAX, size);
			}
		}
	}
	
	//'ㅗ' 모양(총 4개의 모양이 존재)
	public static void shape5() {
		int size = 0;
		for(int i=0; i<N-1; i++) { //1번.
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //2번.
			for(int j=0; j<M-1; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=1; i<N; i++) { //3번.
			for(int j=0; j<M-2; j++) {
				size = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+1];
				MAX = Math.max(MAX, size);
			}
		}
		
		for(int i=0; i<N-2; i++) { //4번.
			for(int j=1; j<M; j++) {
				size = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j-1];
				MAX = Math.max(MAX, size);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N, M 입력
		String [] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		//배열 초기화 및 데이터 입력			
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		//테트로미노 찾기
		shape1();
		shape2();
		shape3();
		shape4();
		shape5();
		
		bw.write(MAX + "\n");
		
		bw.close();
	}
}