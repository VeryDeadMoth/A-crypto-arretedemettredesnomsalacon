package v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

//note : tab_permutation = [3,1,0,2]
//bloc = [2,-1,4,12]
//bloc_permut� = [12,-1,2,4]

public class DES {
	//CONSTANTES
	int taille_bloc = 64;
	int taille_sous_bloc = 32;
	int nb_ronde = 1;
	//tab_decalage consid�r�e constante pour l'instant
	int[] tab_decalage = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	int [] perm_initiale = {58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
	int [] S = {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13};
	int [] E = {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};
	
	//VARIABLES
	int[] masterKey;
	int[] tab_cles;
	
	//EXTRA
	public void TabToString(int[] tab) {
		String str = "[";
		for(int i: tab) {
			str+=i+", ";
		}
		//str.charAt(str.length()-1)="]";
		System.out.println(str);
	}
	
	//CONSTRUCTEUR
	public DES() {
		Random rnd = new Random();
		
		tab_cles = new int[nb_ronde];
		masterKey = new int[taille_bloc];
		for(int k = 0; k<taille_bloc;k++) {
			masterKey[k]=rnd.nextInt(2);
		}
	}
	
	//AUTRE FONCTIONS
	//done
	public int[] stringToBits(String message) {
		byte[] strBytes = message.getBytes();
		int[] res;
		String strBinary ="";
		
		//passage de bytes � string de bits
		for(byte b : strBytes) {
			strBinary += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');;
		}
		
		//passage de string � liste de int
		res = new int[strBinary.length()];
		for(int k = 0 ; k<strBinary.length();k++) {
			res[k]=Character.getNumericValue(strBinary.charAt(k));
		}
		
		return res;
	}
	
	
	public String bitsToString(int[] blocs) {
		int nbFor = blocs.length /8;
		
		//convertir liste de int en string
		/*StringBuilder strBits = new StringBuilder("");
		strBits.append(blocs);*/
		
		//d�coupage du string en substring de taille 8
		String[] strBytes = new String[nbFor]; 
		
		for(int k = 0 ; k < nbFor ; k++) {
			//strBytes[k] = strBits.substring(k*8,k*8 +8);
		}
		
		
		byte b = Byte.parseByte(strBytes[0],2);
		byte[] test = {b};
		System.out.println(new String(test));
		
		
		return null;
	}
	
	public int[] crypte(String message_clair) {
		return null;
	}
	
	public int[] generePermutation() {
		int[] perm = new int[64];
		LinkedList<Integer> init = new LinkedList<Integer>(); 
		Random rand = new Random();
		for(int i =1; i<65; i++) {
			init.add(i);
		}
		for (int i=0; i<64; i++) {
			int t = init.remove(rand.nextInt(0, init.size()));
			perm[i] = t;
		}
		return perm;
	}
	
	public ArrayList<ArrayList<Integer>> decoupage(ArrayList<Integer> block, int nbBlock){
		
		ArrayList<ArrayList<Integer>> myTab = new ArrayList<ArrayList<Integer>>();
		while (block.size() % nbBlock != 0) {
			block.add(0);
		}
		
		while(block.size()>0) {
			ArrayList<Integer> aBlock = new ArrayList<>();
			for(int i=0; i<nbBlock; i++) {
				aBlock.add(block.remove(0));
			}
			myTab.add(aBlock);
		}
		
		return myTab;
	}
		
}
