package lifegame;

import java.util.LinkedList;
import java.util.List;

//盤面の状態を何らかの形でデータとして表現する。
public class Board {
	  public  int N,w,h;
	  private boolean[][] board;
	  private boolean[][] memory;
	  LinkedList<boolean[][]> array = new LinkedList<boolean[][]>();
	
	  	public Board(int size){
			// size をフィールド(１辺のセル数)に記憶する。
			N = size;
			//盤面データを表現する　size * size の二次元配列を確保する。
			// board : board[][] のセルの生死を返す。
			board =  new boolean [size][size];	
		}
	
	public int getSize(){
		return N;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight(){
		return h;
	}
	
	//0からN-1の値を取る整数の組み(x,y)でセルを指定できる。
	public boolean isAlive(int x, int y){
		if ( x<0 || y<0 || x>(N-1) || y>(N-1) )
		return false;
  
        if ( board[x][y] == true ) 
			return board[x][y];
		else return false;
	
	}
	//盤面の(x,y)で指定されるセルの状態を反転する。
	public void switchLife(int x,int y){
		memory = board;//[x][y]はいらない。
		array.add(memory);
	    //x,yが盤面の中を指しているときは、セル(x,y)の生死を切り替える。
		//x,yが盤面の外のときは、何もしない。
		if ((x>=0) && (y>=0) && (x < N) && (y < N)){
			board[x][y] = !board[x][y];
			}
		else{
			board[x][y] = board[x][y]; 
		}
	}
	
	//あるセルの周囲に生きているセルの数を数える。
  	private int countLivingCells(int x, int y){
  		int num=0;
  		 if (isAlive(x-1,y-1)==true) num++;
  		 if (isAlive(x-1,y)==true) num++;
  		 if (isAlive(x-1,y+1)==true) num++;
  		 if (isAlive(x,y-1)==true) num++;
  		 if (isAlive(x,y+1)==true) num++;
  		 if (isAlive(x+1,y-1)==true) num++;
  		 if (isAlive(x+1,y)==true) num++;
  		 if (isAlive(x+1,y+1)==true) num++;
  		return num;
        }
  	
	//現在の盤面の状態をもとに、次の世代の盤面の状態に進める。
	public void nextState(){
		boolean[][] tmp = new boolean[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){		
		tmp[i][j] = board[i][j];
		
			}
		}
		array.add(tmp);
		while(array.size() > 16){
  			array.removeFirst();
  		}
		
		//全てのセルについて周囲8セルの生死の状態を調べ、新しい状態を計算する。
		Board after = new Board(N);
  		for (int j=0; j<N; j++){
  			for (int i=0; i<N; i++)
  				after.board[i][j] = board[i][j];
  			}
  		for (int n=0; n<N; n++){
  			for (int m=0; m<N; m++){
  				if (isAlive(m,n)==true){
  					if (countLivingCells(m,n) == 2 || countLivingCells(m,n) == 3)//セルの生存条件	
  						after.board[m][n] = board[m][n];
  					else after.board[m][n] = !board[m][n];
  				}
  				else {
  					if (countLivingCells(m,n) == 3){//セルの生き返り条件
  						after.board[m][n] = !board[m][n];
  					}
  					else after.board[m][n] = board[m][n];
  				}
  				
  			}
  		}
  		for (int y=0; y<N; y++){
  			for (int x=0; x<N; x++)
  				board[x][y] = after.board[x][y];
  		}
  		}
	
	public void undoState(){
		 boolean[][] hairetu;
		hairetu = array.getLast();
  				board = hairetu;
  				array.removeLast();
	}
	
	
}


