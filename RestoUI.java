package gestion_restaurent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class RestoUI extends JFrame{
	public RestoUI() {
		//set of the frame//
		setTitle("Gestion Restaurant");
		setSize(1200,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	//panel creation//
	JPanel panelActions = new JPanel();
	panelActions.setLayout(new GridLayout(20,20,20,20));
	
	add(panelActions, BorderLayout.EAST);
	JButton openmenu= new JButton("menu");
	openmenu.addActionListener(e ->{
		new MenuUI();
	});
	panelActions.add(openmenu);
	setVisible(true);
	
	}
	public static void main(String[] args) {
	new RestoUI();
		

	}
	

}
