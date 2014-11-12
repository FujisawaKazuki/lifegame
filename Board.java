package lifegame;

import java.util.LinkedList;
import java.util.List;

//�Ֆʂ̏�Ԃ����炩�̌`�Ńf�[�^�Ƃ��ĕ\������B
public class Board {
	  public  int N,w,h;
	  private boolean[][] board;
	  private boolean[][] memory;
	  LinkedList<boolean[][]> array = new LinkedList<boolean[][]>();
	
	  	public Board(int size){
			// size ���t�B�[���h(�P�ӂ̃Z����)�ɋL������B
			N = size;
			//�Ֆʃf�[�^��\������@size * size �̓񎟌��z����m�ۂ���B
			// board : board[][] �̃Z���̐�����Ԃ��B
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
	
	//0����N-1�̒l����鐮���̑g��(x,y)�ŃZ�����w��ł���B
	public boolean isAlive(int x, int y){
		if ( x<0 || y<0 || x>(N-1) || y>(N-1) )
		return false;
  
        if ( board[x][y] == true ) 
			return board[x][y];
		else return false;
	
	}
	//�Ֆʂ�(x,y)�Ŏw�肳���Z���̏�Ԃ𔽓]����B
	public void switchLife(int x,int y){
		memory = board;//[x][y]�͂���Ȃ��B
		array.add(memory);
	    //x,y���Ֆʂ̒����w���Ă���Ƃ��́A�Z��(x,y)�̐�����؂�ւ���B
		//x,y���Ֆʂ̊O�̂Ƃ��́A�������Ȃ��B
		if ((x>=0) && (y>=0) && (x < N) && (y < N)){
			board[x][y] = !board[x][y];
			}
		else{
			board[x][y] = board[x][y]; 
		}
	}
	
	//����Z���̎��͂ɐ����Ă���Z���̐��𐔂���B
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
  	
	//���݂̔Ֆʂ̏�Ԃ����ƂɁA���̐���̔Ֆʂ̏�Ԃɐi�߂�B
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
		
		//�S�ẴZ���ɂ��Ď���8�Z���̐����̏�Ԃ𒲂ׁA�V������Ԃ��v�Z����B
		Board after = new Board(N);
  		for (int j=0; j<N; j++){
  			for (int i=0; i<N; i++)
  				after.board[i][j] = board[i][j];
  			}
  		for (int n=0; n<N; n++){
  			for (int m=0; m<N; m++){
  				if (isAlive(m,n)==true){
  					if (countLivingCells(m,n) == 2 || countLivingCells(m,n) == 3)//�Z���̐�������	
  						after.board[m][n] = board[m][n];
  					else after.board[m][n] = !board[m][n];
  				}
  				else {
  					if (countLivingCells(m,n) == 3){//�Z���̐����Ԃ����
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


