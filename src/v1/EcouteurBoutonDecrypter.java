package v1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class EcouteurBoutonDecrypter implements MouseListener {
	private JTextField jt;
	private JLabel l;
	
	public EcouteurBoutonDecrypter(JTextField jt, JLabel l) {
		this.jt = jt;
		this.l = l;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/*DES d = new DES(); 
		String str = jt.getText();
		String out = d.Decrypte(str);
		l.setText(out);*/
		
		l.setText("bbb");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
