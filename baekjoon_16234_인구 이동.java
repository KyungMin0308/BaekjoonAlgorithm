import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//나라 클래스
class Country {
	private int x; //x좌표
	private int y; //y좌표
	private int population; //인구수
	
	public Country(int x, int y, int po) {
		this.x = x;
		this.y = y;
		this.population = po;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getPopulation() { return this.population; }
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N, L, R;
	static int [][] countries; //나라 배열
	static boolean [][] visited; //방문 배열
	static ArrayList<ArrayList<Country>> landList = new ArrayList<ArrayList<Country>>(); //연합 리스트
	
	static int [] dx = { 0, -1, 0, 1 };
	static int [] dy = { 1, 0, -1, 0 };
	
	static int DAY = 0; //인구 이동 일
	
	//인구 이동
	public static void move(ArrayList<ArrayList<Country>> landList) {
		int sum, div;
		ArrayList<Country> lst;
		//이동할 인구수 계산
		for(int i=0; i<landList.size(); i++) {
			sum = 0;
			div = 0;
			lst = landList.get(i);
			
			if(lst.size() == 0) continue; //리스트 크기가 0이면 continue
			
			for(Country coun: lst) {
				sum += coun.getPopulation();
			}
			div = sum / lst.size();
			
            //이동할 인구 수로 갱신
			for(Country coun: lst) {
				countries[coun.getX()][coun.getY()] = div;
			}
		}
	}
	
	//연합 파악
	public static void union(int x, int y, int id) {
		if(visited[x][y]) return; //이미 방문한 위치면 리턴
		
		//현재 위치 방문 처리 및 현재 연합에 추가
		visited[x][y] = true;
		landList.get(id).add(new Country(x, y, countries[x][y]));
		
		//인접한 나라 중 연합 가능한 나라를 찾음
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위를 넘어가거나 인구 차이 범위 밖이라면 continue
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(L > Math.abs(countries[x][y] - countries[nx][ny]) 
					|| Math.abs(countries[x][y] - countries[nx][ny]) > R) continue;
			
			union(nx, ny, id);
		}
	}
	
	//종료 조건
	public static boolean isOk() {
		for(int i=0; i<landList.size(); i++) {
			if(landList.get(i).size() != 1) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		String [] nlr = br.readLine().split(" ");
		N = Integer.parseInt(nlr[0]);
		L = Integer.parseInt(nlr[1]);
		R = Integer.parseInt(nlr[2]);
		
		//나라 배열 초기화 및 데이터 입력
		countries = new int[N][N];
		for(int i=0; i<N; i++) {
			String [] c = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				countries[i][j] = Integer.parseInt(c[j]);
			}
		}
		
		//이중 리스트 초기화
		for(int i=0; i<N*N; i++) {
			landList.add(new ArrayList<Country>());
		}
		
		while(true) {
			//연합 파악
			visited = new boolean[N][N];
			int id = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					
					union(i, j, id);
					id++;
				}
			}
			
			//종료 여부 확인
			if(isOk()) break;
			
			//인구 이동
			move(landList);
			
			//일 증가
			DAY++;
			
			//리스트 비우기
			for(int i=0; i<landList.size(); i++) {
				landList.get(i).clear();
			}
		}
		
		bw.write(DAY + "\n");
		
		bw.close();
	}
}