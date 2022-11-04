package v1;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import java.util.Queue;

//note : tab_permutation = [3,1,0,2]
//bloc = [2,-1,4,12]
//bloc_permutï¿½ = [12,-1,2,4]

public class DES {
	//CONSTANTES
	int taille_bloc = 64;
	int taille_sous_bloc = 32;
	int nb_ronde = 1;
	//tab_decalage considï¿½rï¿½e constante pour l'instant
	int[] tab_decalage = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	ArrayList<Integer> perm_initiale = new ArrayList<Integer>(List.of(58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7));
	
	int [] S = {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13};
	int [] E = {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};
	
	//VARIABLES
	ArrayList<Integer> masterKey;
	ArrayList<ArrayList<Integer>> tab_cles;
	
	//EXTRA
	/*public void TabToString(int[] tab) {
		String str = "[";
		for(int i: tab) {
			str+=i+", ";
		}
		//str.charAt(str.length()-1)="]";
		System.out.println(str);
	}*/
	
	//CONSTRUCTEUR ******************************************************************************************
	public DES() {
		Random rnd = new Random();
		
		this.tab_cles = new ArrayList<ArrayList<Integer>>();
		this.masterKey = new ArrayList<Integer>();
		for(int k = 0; k<taille_bloc;k++) {
			this.masterKey.add(rnd.nextInt(2));
		}
	}
	
	//AUTRE FONCTIONS ***************************************************************************************
	
	//stringToBits
	public int[] stringToBits(String message) {
		byte[] strBytes = message.getBytes();
		int[] res;
		String strBinary ="";
		
		//passage de bytes ï¿½ string de bits
		for(byte b : strBytes) {
			strBinary += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');;
		}
		
		//passage de string ï¿½ liste de int
		res = new int[strBinary.length()];
		for(int k = 0 ; k<strBinary.length();k++) {
			res[k]=Character.getNumericValue(strBinary.charAt(k));
		}
		
		return res;
	}
	
	//bitsToString
	public int sByteToInt(String bytes) {
		int[] ref = {128,64,32,16,8,4,2,1};
		int s = 0;
		for(int i = 0; i<8;i++) {
			if (bytes.charAt(i)=='1') {
				s+=ref[i];
			}
		}
		return s;
	}
	
	public String bitsToString(int[] blocs) {
		byte[] b = new byte[blocs.length];
		
		
		for(int i = 0; i<blocs.length/8; i++) {
			String s ="";
			for (int j = 0; j<8; j++) {
				s += blocs[i*8+j];
			}
			
			b[i] = (byte) sByteToInt(s);
		}
		
		String str = new String(b, StandardCharsets.UTF_8);
		return str;}
	
	//generePermutation
	/*public int[] generePermutation() {
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
	}*/
	
	public ArrayList<Integer> generePermutation(int taille) {
		ArrayList<Integer> newPermutation = new ArrayList<Integer>();
		for (int k = 1; k <= taille;k++) {
			newPermutation.add(k);
		}
		
		Collections.shuffle(newPermutation);
		
		return newPermutation;
	}
	
	//decoupage
	public ArrayList<ArrayList<Integer>> decoupage(ArrayList<Integer> block, int tailleBlock){
		ArrayList<Integer>blCopy = new ArrayList<Integer>();
		for(int i : block) {
			blCopy.add(i);
		}
		
		ArrayList<ArrayList<Integer>> myTab = new ArrayList<ArrayList<Integer>>();
		while (blCopy.size() % tailleBlock != 0) {
			blCopy.add(0);
		}
		
		while(blCopy.size()>0) {
			ArrayList<Integer> aBlock = new ArrayList<>();
			for(int i=0; i<tailleBlock; i++) {
				aBlock.add(blCopy.remove(0));
			}
			myTab.add(aBlock);
		}
		
		return myTab;
	}
	
	//permutation
	public ArrayList<Integer> permutation(ArrayList<Integer> block, ArrayList<Integer> perm){
		ArrayList<Integer> blPerm = new ArrayList<Integer>();
		for(int i = 0; i<perm.size(); i++) {
			blPerm.add(block.get(perm.get(i)-1));
		}
		return blPerm;
	}
	
	//invPermutation
	public ArrayList<Integer> invPermutation(ArrayList<Integer> block, ArrayList<Integer> perm){
		ArrayList<Integer> blPerm = new ArrayList<Integer>();
		for(int i = 1; i<perm.size()+1; i++) {
			int ind = perm.indexOf(i);
			blPerm.add(block.get(ind));
		}
		return blPerm;
	}
	
	//recollage_bloc
	
	public ArrayList<Integer> recollage_bloc(ArrayList<ArrayList<Integer>> decoupTable){
		ArrayList<Integer> tableRecolle = new ArrayList<Integer>();
		for(ArrayList<Integer> i : decoupTable) {
			for(int j :i) {
				tableRecolle.add(j);
			}
		}
		return tableRecolle;
	}
	
	//decalle_gauche
	public ArrayList<Integer> decalle_gauche(ArrayList<Integer> bloc, int nbCran){
		
		ArrayList<Integer> shiftedBloc = (ArrayList<Integer>) bloc.clone();
		Collections.rotate(shiftedBloc,-nbCran);
		
		return shiftedBloc;
	}
	
	//xor
	public ArrayList<Integer> xor(ArrayList<Integer> tab1, ArrayList<Integer> tab2){
		
		ArrayList<Integer> xorTab = new ArrayList<Integer>();
		
		for(int k = 0 ; k < tab1.size() ; k++) {
			xorTab.add(tab1.get(k) ^ tab2.get(k));
		}
		
		return xorTab;
	}
	
	//genereCle
	public void genereCle(int n) {
		
		//key issue de la permutation de master key
		ArrayList<Integer> key = permutation(this.masterKey,generePermutation(64));
		
		//retirer les 8 derniers bits
		//découpage
		//decallage (tab decallage)
		//collage
		//permutation 2
		//retirer les 8 derniers bits
		
		//stockage dans tab_clés
		tab_cles.add(key);
	}
	
	public void Crypte(String mess) {
		
	}
	
}
