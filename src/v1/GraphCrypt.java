package v1;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class GraphCrypt extends JFrame{
	
	public JLabel l1, l2,l3;
	public JTextField jt1;
	public JButton b1, b2, b3;
	
	public DES d1,d2,d3;
	public HashMap<Integer,ArrayList<ArrayList<Integer>>> keys;
	
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
		
		b1.addMouseListener(new EcouteurBoutonCrypter(this));
		b2.addMouseListener(new EcouteurBoutonDecrypter(this));
		b3.addMouseListener(new Copy(l3,this));
		
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
		
		DESBuilder();
	}
	
	public void DESBuilder() {
		this.d1 = new DES();
		this.d2 = new DES();
		this.d3 = new DES();

		this.keys = new HashMap<>();
		
	}
	
	public static void main(String[] args) {
		GraphCrypt c = new GraphCrypt();
	}
	
	public void deserialisation(String fileName) throws IOException {
		
		Path pathOfFile = Path.of(fileName);
		String temporaryFileString = Files.readString(pathOfFile);
		
		Gson gson = new Gson();
		
		java.lang.reflect.Type type = new TypeToken<HashMap<Integer,ArrayList<ArrayList<Integer>>>>(){}.getType();
		
		this.keys = gson.fromJson(temporaryFileString, type);
		System.out.println(keys);
	}
	
	public void crypteMessage() {
		String crStr1 = "";
		String crStr2 = "";
		String crStr3 = "";
		String str1 = jt1.getText();
		
		//crypt 1
		ArrayList<Integer> cr1 = d1.crypte(str1);
				
		for(int i : cr1) {
			crStr1+=i;
		}
		
		this.keys.put(1, d1.tab_cles);
		
		//crypt 2
		
		ArrayList<Integer> cr2 = d2.crypte(crStr1);
		
		for(int i : cr2) {
			crStr2+=i;
		}
		
		this.keys.put(2, d2.tab_cles);
		
		ArrayList<Integer> cr3 = d3.crypte(crStr2);
		
		for(int i : cr3) {
			crStr3+=i;
		}
		
		this.keys.put(3, d3.tab_cles);
		
		l3.setText(crStr3);
	}
	
	
	public void decrypteMessage() throws IOException {
		
		//SELECTION DE LA CLE
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
		
        //JSON DECODE
        deserialisation(chooser.getSelectedFile().toString());
        
		this.d1.tab_cles = this.keys.get(1);
		this.d2.tab_cles = this.keys.get(2);
		this.d3.tab_cles = this.keys.get(3);
		
		String crStr3 = jt1.getText();
		
		int value;
		
		ArrayList<Integer> cr3 = new ArrayList<Integer>();
		for(int k = 0 ; k<crStr3.length();k++) {
			value = Character.getNumericValue(crStr3.charAt(k));
			if(value==1 || value==0) {
				cr3.add(value);
			}
			
		}
		
		
		String str3 = d3.decrypte(cr3);
		ArrayList<Integer> decryptedMessage3 = new ArrayList<Integer>();
		for(int k = 0 ; k<str3.length();k++) {
			value = Character.getNumericValue(str3.charAt(k));
			if(value==1 || value==0) {
				decryptedMessage3.add(value);
			}
		}
		
		String str2 = d2.decrypte(decryptedMessage3);
		ArrayList<Integer> decryptedMessage2 = new ArrayList<Integer>();
		for(int k = 0 ; k<str2.length();k++) {
			value = Character.getNumericValue(str2.charAt(k));
			if(value==1 || value==0) {
				decryptedMessage2.add(value);
			}
		}
		
		l3.setText(d1.decrypte(decryptedMessage2));
	}


}
