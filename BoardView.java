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
		this.field = new Board(10);//10����ꂽ�B10��ς���ƌ��ς��B
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g){
		int width = this.getWidth();
		int height = this.getHeight(); 
		int size = field.getSize();//Board.getSize()����Ȃ�
		super.paint(g);// JPanel �̕`�揈���i�w�i�h��Ԃ��j


		for (int n=0;n<size;n++){
			for (int m=0;m<size;m++){
				if (field.isAlive(n,m)==true){
					g.setColor(Color.red);
					g.fillRect(width/(size+2)*(m+1),height/(size+2)*(n+1), width/(size+2)-1,height/(size+2)-1);
				}
				else {//�ŏ��͂��̏��
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
		//�}�E�X�̍��W���Z��(x,y)�̍��W�͈͓̔��ł���΁A
		for (int n=0;n<field.getSize();n++){
			if ((e.getY() > getHeight()/(field.getSize()+2)*(n+1)) && (e.getY() < getHeight()/(field.getSize()+2)*(n+2))){
				for (int m=0;m<field.getSize();m++){	
					if ((e.getX() > getWidth()/(field.getSize()+2)*(m+1)) && ( e.getX() < getWidth()/(field.getSize()+2)*(m+2))){
						//Board��switchLife(x,y)���Ăяo���ĔՖʃf�[�^���X�V���A
						field.switchLife(n,m);
						px=n;
						py=m;
						//�Ֆʂ��ĕ`�悷��B
						this.repaint();
					}
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e){
	}
	public void mouseDragged(MouseEvent e){
		//�}�E�X�̍��W���Z���̊O�ł���΁A�u���O�ɔ��]�����Z���͂Ȃ��v��Ԃɂ���B
		for (int n=0;n<field.getSize();n++){
			if ((e.getY() > getHeight()/(field.getSize()+2)*(n+1)) && (e.getY() < getHeight()/(field.getSize()+2)*(n+2))){
				for (int m=0;m<field.getSize();m++){	
					if ((e.getX() > getWidth()/(field.getSize()+2)*(m+1)) && ( e.getX() < getWidth()/(field.getSize()+2)*(m+2))){
						//�}�E�X�̍��W���Z��(x,y)�ɑΉ����Ă��āA
						//���A���O�ɔ��]�����Z���̍��W�łȂ���΁A
						if((n != px) || (m != py)){
							px =n;py=m;
							//(x,y)�̃Z���̏�Ԃ�ύX���A
							field.switchLife(n,m);
							//�Ֆʂ��ĕ`�悷��B
							this.repaint();
						}


					}
				}}}
	}
	public void mouseMoved(MouseEvent e){
	}

	public void nextState(){
		//�u���̐���֐i�߂�v���\�b�h��錾����B
		field.nextState();
		this.repaint();
	}
	public void undoState(){
		//�u�O�̐���֐i�߂�v���\�b�h��錾����B
		field.undoState();
		this.repaint();
	}
	public class NextButtonListener implements ActionListener{
		//��������̃R���X�g���N�^�̐錾�̗�
		BoardView field;
		public NextButtonListener(BoardView view){
			//view���t�B�[���h�ɕۊǂ��Ă���
			field = view;
		}
		//�{�^���������ꂽ�Ƃ��ɌĂяo����郁�\�b�h
		public void actionPerformed(ActionEvent e){
			//view�ɑ΂��Đ���̐i�s���w������B
			field.nextState();
		}
	}
	public class UndoButtonListener implements ActionListener{
		//��������̃R���X�g���N�^�̐錾�̗�
		BoardView field;
		public UndoButtonListener(BoardView view){
			//view���t�B�[���h�ɕۊǂ��Ă���
			field = view;
		}
		//�{�^���������ꂽ�Ƃ��ɌĂяo����郁�\�b�h
		public void actionPerformed(ActionEvent e){
			//view�ɑ΂��Đ���̐i�s���w������B
			field.undoState();
		}
	}
}


