package v1;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.google.gson.Gson;



public class Copy implements MouseListener{
	private JLabel l;
	public GraphCrypt g;
	
	public Copy(JLabel l, GraphCrypt g) {
		this.l = l;
		this.g = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String myString = l.getText();
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		
		//CREATION CLE
		Gson gson = new Gson();
		
		
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(l) == JFileChooser.APPROVE_OPTION) {
			try {
		         FileWriter file = new FileWriter(chooser.getSelectedFile()+".json");
		         file.write(gson.toJson(g.keys));
		         file.close();
		      } catch (IOException err) {
		         err.printStackTrace();
		      }
		}
		
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
