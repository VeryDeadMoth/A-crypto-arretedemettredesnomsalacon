package v1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class EcouteurBoutonDecrypter implements MouseListener {
	
	public GraphCrypt g;
	
	public EcouteurBoutonDecrypter(GraphCrypt g) {
		this.g = g;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		g.decrypteMessage();
		
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
