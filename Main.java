package lifegame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
	public static void main(String[] args){
		//create window
		final JFrame frame = new JFrame();
		frame.setTitle("Lifegame 09B09068");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create base panel
		final JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,400));
		frame.setMinimumSize(new Dimension(300,300));
		
		base.setLayout(new BorderLayout());
		final BoardView view = new BoardView();
		base.add(view, BorderLayout.CENTER);
		
		final JPanel buttonPanel = new JPanel();//�{�^���p�p�l�����쐬���A
		base.add(buttonPanel, BorderLayout.SOUTH);//base�̉��[�ɔz�u����B
		buttonPanel.setLayout(new FlowLayout());
		final JButton next = new JButton("Next"); 
		final JButton undo = new JButton("Undo");
		buttonPanel.add(next);
		buttonPanel.add(undo);
		//undo.setEnabled(false);
		
		//Next�{�^���ɃC�x���g����������
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.nextState();				
			    }});
		
		//Undo�{�^���ɃC�x���g����������
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.undoState();				
				}});
		
		//GUI���i�̔z��������������B
		frame.pack();
		//�E�B���h�E��\������B
		frame.setVisible(true);
		
		
		}
	}
	
	
