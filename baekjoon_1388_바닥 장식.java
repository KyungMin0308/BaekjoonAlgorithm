import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {

		//N, M 입력
		String [] nm = br.readLine().split(" ");
		
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		char [][] graph = new char[N][M]; //바닥 그래프
		boolean [][] visited = new boolean[N][M]; //방문 표시
		int floor = 0; //나무 판자 개수
		
		//바닥 장식 입력
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				graph[i][j] = str.charAt(j);
			}
		}
		
		//나무 판자 개수 세기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//이미 방문했다면 넘어감
				if(visited[i][j]) continue;
				
				//장식이 - 라면
				if(graph[i][j] == '-') {
					visited[i][j] = true; //방문 처리
					int idx = j; //시작 인덱스
					while(idx != M) { //오른쪽으로 이동하면서 -가 있는지 확인
						if(graph[i][idx] != '-')
							break;
						if(graph[i][idx] == '-')
							visited[i][idx] = true;
						idx++;
					}
					floor++; //나무 판자 개수 증가
				}
				
				//장식이 | 라면
				else if(graph[i][j] == '|') {
					visited[i][j] = true; //방문 처리
					int idx = i; //시작 인덱스
					while(idx != N) { //아래로 이동하면서 |가 있는지 확인
						if(graph[idx][j] != '|')
							break;
						if(graph[idx][j] == '|')
							visited[idx][j] = true;
						idx++;
					}
					floor++; //나무 판자 개수 증가
				}
			}
		}
		
		//결과 출력
		bw.write(floor + "\n");
		
		bw.close();
	}
}