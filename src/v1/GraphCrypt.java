package v1;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class GraphCrypt extends JFrame{
	
	public JLabel l1, l2,l3;
	public JTextField jt1;
	public JButton b1, b2, b3;
	
	public GraphCrypt() {
		super("crypter/décrypter");
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(2,1));
		JPanel p3 = new JPanel(new GridLayout(2,1));
		JPanel p2 = new JPanel(new GridLayout(1,3));
		this.l1 = new JLabel("votre message : ");
		this.l2 = new JLabel("Sortie : ");
		this.l3 = new JLabel("");
		this.jt1 = new JTextField();
		this.b1 = new JButton("crypter");
		this.b2 = new JButton("decrypter");
		this.b3 = new JButton("copier la sortie");
		
		b1.addMouseListener(new EcouteurBoutonCrypter(jt1, l3));
		b2.addMouseListener(new EcouteurBoutonDecrypter(jt1, l3));
		b3.addMouseListener(new Copy(l3));
		
		p1.add(l1);
		p3.add(jt1);
		p1.add(l2);
		p3.add(l3);
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		this.add(p1,BorderLayout.WEST);
		this.add(p3,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		this.setSize(400,250);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		GraphCrypt c = new GraphCrypt();

	}

}
