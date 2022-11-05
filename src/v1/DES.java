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
//bloc_permut� = [12,-1,2,4]

public class DES {
	//CONSTANTES
	int taille_bloc = 64;
	int taille_sous_bloc = 32;
	int nb_ronde = 1;
	//tab_decalage consid�r�e constante pour l'instant
	int[] tab_decalage = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	ArrayList<Integer> perm_initiale = new ArrayList<Integer>(List.of(58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7));
	
	int [] S = {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13};
	ArrayList<Integer> E = new ArrayList<Integer>(List.of(32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1));
	
	//VARIABLES
	ArrayList<Integer> masterKey;
	ArrayList<ArrayList<Integer>> tab_cles;
	
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
	public ArrayList<Integer> stringToBits(String message) {
		byte[] strBytes = message.getBytes();
		String strBinary ="";
		
		//passage de bytes � string de bits
		for(byte b : strBytes) {
			strBinary += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		}
		
		//passage de string � liste de int
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int k = 0 ; k<strBinary.length();k++) {
			res.add(Character.getNumericValue(strBinary.charAt(k)));
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
	
	public String bitsToString(ArrayList<Integer> blocs) {
		byte[] b = new byte[blocs.size()];
		
		
		for(int i = 0; i<blocs.size()/8; i++) {
			String s ="";
			for (int j = 0; j<8; j++) {
				s += blocs.get(i*8+j);
			}
			
			b[i] = (byte) sByteToInt(s);
		}
		
		String str = new String(b, StandardCharsets.UTF_8);
		return str;}
	
	//generePermutation
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
		
		//key issue de la permutation de master key (retirer les 8 derniers bits)
		ArrayList<Integer> key = permutation(new ArrayList<Integer>(this.masterKey.subList(0,56)),generePermutation(56));
		
		System.out.println("cl� " + key);
		
		//d�coupage
		ArrayList<Integer> splitKey1 = new ArrayList<Integer>(key.subList(0, 28));
		ArrayList<Integer> splitKey2 = new ArrayList<Integer>(key.subList(28, 56));
		
		System.out.println("split 1 " + splitKey1);
		System.out.println("split 2 " +splitKey2);
		
		//decallage (tab decallage)
		splitKey1 = decalle_gauche(splitKey1,tab_decalage[n]);
		splitKey2 = decalle_gauche(splitKey2,tab_decalage[n]);
		
		System.out.println("shifted split 1 " +splitKey1);
		System.out.println("shifted split 2" +splitKey2);
		
		//collage
		splitKey1.addAll(splitKey2);
		
		System.out.println("collage " +splitKey1);
		
		//permutation 2 (retirer les 8 derniers bits)+ stockage dans tab_cl�s
		tab_cles.add(permutation(new ArrayList<Integer>(splitKey1.subList(0,48)),generePermutation(48)));
	}
	
	//fonction S
	public ArrayList<Integer> fonction_S(ArrayList<Integer> tab){
		
		String strLine = tab.get(0).toString() + tab.get(5);
		String strColumn = "";
		for (int i : tab.subList(1,5)) {
			strColumn+=i;
		}
		
		String newStr = "";
		newStr += String.format("%4s", Integer.toBinaryString(S[16*Integer.parseInt(strLine, 2) + Integer.parseInt(strColumn,2)])).replace(' ', '0');
		
		
		//System.out.println("fetched number from tab S");
		//System.out.println(newStr);
		
		ArrayList<Integer> newTab = new ArrayList<Integer>();
		for(int k = 0; k<newStr.length();k++) {
			newTab.add(Character.getNumericValue(newStr.charAt(k)));
		}
		
		return newTab;
	}
	
	//fonction F
	public ArrayList<Integer> fonction_F (ArrayList<Integer> cle, ArrayList<Integer> D)
	{
		ArrayList<Integer> DnPrime = permutation(D, E);
		ArrayList<Integer> DnEtoile = xor(DnPrime, cle);
		ArrayList<ArrayList<Integer>> Ldn = decoupage(DnEtoile, 6);
		ArrayList<ArrayList<Integer>> Sldn = new ArrayList<ArrayList<Integer>>();
		
		for(ArrayList<Integer> i : Ldn) {
			Sldn.add(fonction_S(i));
		}
		
		ArrayList<Integer> recolle = recollage_bloc(Sldn);
		
		return recolle;
	}
	
	public ArrayList<Integer> crypte(String message_clair) {
		//changer en bits
		ArrayList<Integer> messageBits = stringToBits(message_clair);
		//decoupage en blocs de 64 bits
		ArrayList<ArrayList<Integer>> blocs64 = decoupage(messageBits,64);
		
		//pour chaque bloc :
		ArrayList<Integer> Gn,Dn,Gn1,Dn1;
		Gn1 = new ArrayList<Integer>();
		Dn1 = new ArrayList<Integer>();
		
		for(int k = 0; k<blocs64.size(); k++) {
			//perm initiale
			blocs64.set(k, permutation(blocs64.get(k),perm_initiale));
			//decoupage en 2 blocs de 32
			Gn = new ArrayList<Integer> (blocs64.get(k).subList(0,32));
			Dn = new ArrayList<Integer> (blocs64.get(k).subList(32,64));
			
			//rondes
			for(int i=0; i<nb_ronde;i++) {
				//generation cl�
				genereCle(i);
				Dn1= xor(Gn,fonction_F(tab_cles.get(i),Dn));
				Gn1 = Dn;	
			}
			
			//recollage en bloc de 64
			Gn1.addAll(Dn1);
			blocs64.set(k,Gn1);
			
			//perm inv
			blocs64.set(k, invPermutation(blocs64.get(k),perm_initiale));
		}
		
		//recomposition du message
		return recollage_bloc(blocs64);
	}
	
	//decrypte
	public String decrypte(ArrayList<Integer> message_code) {
		//decoupage en blocs de 64 bits
		ArrayList<ArrayList<Integer>> blocs64 = decoupage(message_code,64);
		
		//pour chaque bloc :
		ArrayList<Integer> Gn,Dn,Gn1,Dn1;
		Gn1 = new ArrayList<Integer>();
		Dn1 = new ArrayList<Integer>();
		
		for(int k = 0; k<blocs64.size(); k++) {
			//perm initiale
			blocs64.set(k, permutation(blocs64.get(k),perm_initiale));
			//decoupage en 2 blocs de 32
			Gn = new ArrayList<Integer> (blocs64.get(k).subList(0,32));
			Dn = new ArrayList<Integer> (blocs64.get(k).subList(32,64));
			
			//rondes
			for(int i=0; i<nb_ronde;i++) {
				//generation cl�
				genereCle(i);
				Dn1= Gn;
				Gn1 = xor(Dn,fonction_F(tab_cles.get(i),Dn1));
			}
					
			//recollage en bloc de 64 ************************************************** Dn1.addAll ? ou Gn1.addAll ??
			Gn1.addAll(Dn1);
			blocs64.set(k,Gn1);
					
			//perm inv
			blocs64.set(k, invPermutation(blocs64.get(k),perm_initiale));
		}
				
		//recomposition du message
		return bitsToString(recollage_bloc(blocs64));
	}
	
}
