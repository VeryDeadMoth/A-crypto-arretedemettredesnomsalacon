package v1;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class GraphCrypt extends JFrame{
	
	public JLabel l1;
	public JTextField jt1;
	public JButton b1, b2;
	
	public GraphCrypt() {
		super("crypter/décrypter");
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,2));
		JPanel p2 = new JPanel(new GridLayout(1,2));
		this.l1 = new JLabel("votre message : ");
		this.jt1 = new JTextField();
		this.b1 = new JButton("crypter");
		this.b2 = new JButton("decrypter");
		p1.add(l1);
		p1.add(jt1);
		
		p2.add(b1);
		p2.add(b2);
		
		this.add(p1,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		this.setSize(400,250);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		GraphCrypt c = new GraphCrypt();

	}

}
