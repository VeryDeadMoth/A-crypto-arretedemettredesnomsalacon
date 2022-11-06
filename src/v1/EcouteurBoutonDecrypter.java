package v1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
		DES d = new DES(); 
		String str = jt.getText();
		str = str.replace(" ", "");
		str = str.replace(",", "");
		str = str.replace("[", "");
		str = str.replace("]", "");
		
		ArrayList<Integer> input = new ArrayList<Integer>();
		for(int i=0; i<str.length(); i++) {
			input.add( Integer.parseInt(String.valueOf(str.charAt(i))));
		}
		String out = d.decrypte(input);
		l.setText(out);
		
		
		
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
