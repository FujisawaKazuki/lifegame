package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardView extends JPanel
implements MouseListener, MouseMotionListener{
	private Board field;
	private static int px,py;

	public BoardView(){
		this.field = new Board(10);//10を入れた。10を変えると個数変わる。
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g){
		int width = this.getWidth();
		int height = this.getHeight(); 
		int size = field.getSize();//Board.getSize()じゃない
		super.paint(g);// JPanel の描画処理（背景塗りつぶし）


		for (int n=0;n<size;n++){
			for (int m=0;m<size;m++){
				if (field.isAlive(n,m)==true){
					g.setColor(Color.red);
					g.fillRect(width/(size+2)*(m+1),height/(size+2)*(n+1), width/(size+2)-1,height/(size+2)-1);
				}
				else {//最初はこの状態
					g.setColor(Color.blue);
					g.fillRect(width/(size+2)*(m+1),height/(size+2)*(n+1), width/(size+2)-1,height/(size+2)-1);
				}
			}
		}

	}
	public void mouseClicked(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	public void mousePressed(MouseEvent e){
		//マウスの座標がセル(x,y)の座標の範囲内であれば、
		for (int n=0;n<field.getSize();n++){
			if ((e.getY() > getHeight()/(field.getSize()+2)*(n+1)) && (e.getY() < getHeight()/(field.getSize()+2)*(n+2))){
				for (int m=0;m<field.getSize();m++){	
					if ((e.getX() > getWidth()/(field.getSize()+2)*(m+1)) && ( e.getX() < getWidth()/(field.getSize()+2)*(m+2))){
						//BoardのswitchLife(x,y)を呼び出して盤面データを更新し、
						field.switchLife(n,m);
						px=n;
						py=m;
						//盤面を再描画する。
						this.repaint();
					}
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e){
	}
	public void mouseDragged(MouseEvent e){
		//マウスの座標がセルの外であれば、「直前に反転したセルはない」状態にする。
		for (int n=0;n<field.getSize();n++){
			if ((e.getY() > getHeight()/(field.getSize()+2)*(n+1)) && (e.getY() < getHeight()/(field.getSize()+2)*(n+2))){
				for (int m=0;m<field.getSize();m++){	
					if ((e.getX() > getWidth()/(field.getSize()+2)*(m+1)) && ( e.getX() < getWidth()/(field.getSize()+2)*(m+2))){
						//マウスの座標がセル(x,y)に対応していて、
						//かつ、直前に反転したセルの座標でなければ、
						if((n != px) || (m != py)){
							px =n;py=m;
							//(x,y)のセルの状態を変更し、
							field.switchLife(n,m);
							//盤面を再描画する。
							this.repaint();
						}


					}
				}}}
	}
	public void mouseMoved(MouseEvent e){
	}

	public void nextState(){
		//「次の世代へ進める」メソッドを宣言する。
		field.nextState();
		this.repaint();
	}
	public void undoState(){
		//「前の世代へ進める」メソッドを宣言する。
		field.undoState();
		this.repaint();
	}
	public class NextButtonListener implements ActionListener{
		//引数ありのコンストラクタの宣言の例
		BoardView field;
		public NextButtonListener(BoardView view){
			//viewをフィールドに保管しておく
			field = view;
		}
		//ボタンが押されたときに呼び出されるメソッド
		public void actionPerformed(ActionEvent e){
			//viewに対して世代の進行を指示する。
			field.nextState();
		}
	}
	public class UndoButtonListener implements ActionListener{
		//引数ありのコンストラクタの宣言の例
		BoardView field;
		public UndoButtonListener(BoardView view){
			//viewをフィールドに保管しておく
			field = view;
		}
		//ボタンが押されたときに呼び出されるメソッド
		public void actionPerformed(ActionEvent e){
			//viewに対して世代の進行を指示する。
			field.undoState();
		}
	}
}


