package v1;

import java.util.ArrayList;
import java.util.List;

public class TestDes {

	public static void main(String[] args) {
		DES d = new DES();
		ArrayList<Integer> bitMsg = d.stringToBits("test");
		
		for(int i=0; i<bitMsg.size(); i++) {
			if(i%8 == 0) {
				System.out.print(" ");
			}
			System.out.print(bitMsg.get(i));
		}

		System.out.println();
		//System.out.println(d.bitsToString(bitMsg));
		
		//decoupage
		System.out.println("decoupage :");
		System.out.println(d.decoupage(bitMsg, 10));
		System.out.println(d.decoupage(bitMsg, 8));
		
		//decalle gauche
		
		ArrayList<Integer> bloc1 = new ArrayList<Integer>();
		
		for (int i : bitMsg) {
			bloc1.add(i);
		}
		
		ArrayList<Integer> shiftedBloc1 = d.decalle_gauche(bloc1,2);
		
		System.out.println("decalle gauche :");
		System.out.println(bloc1);
		System.out.println(shiftedBloc1);
		
		//xor
		System.out.println("xor :");
		System.out.println(d.xor(bloc1,shiftedBloc1));
		
		//genere permutation
		System.out.println("genere permutation :");
		System.out.println(d.generePermutation(10));
		System.out.println(d.generePermutation(10));
		
		//genere clé
		System.out.println("genere clé :");
		d.genereCle(0);
		System.out.println(d.tab_cles.get(0));
		
		//fonction S
		System.out.println("fonction S :");
		ArrayList<Integer> bloc2 = new ArrayList<Integer>(List.of(1,1,0,1,1,1));
		
		System.out.println(d.fonction_S(bloc2));
		
		//crypte/decrypte
		DES d1 = new DES();
		
		System.out.println("crypte :");
		ArrayList<Integer> cryptedMessage = d1.crypte("hello world");
		//System.out.println("crypte :" + cryptedMessage);
		System.out.println("decrypte : " + d1.decrypte(cryptedMessage));
		
		
		//triple DES
		DES d2 = new DES();
		DES d3 = new DES();
		
		String crStr = "";
		String cr2Str = "";
		
		for(int i : cryptedMessage) {
			crStr+=i;
		}
		
		ArrayList<Integer> cryptedMessage2 = d2.crypte(crStr);
		
		for(int i : cryptedMessage2) {
			cr2Str+=i;
		}
		
		ArrayList<Integer> cryptedMessage3 = d3.crypte(cr2Str);

		
		System.out.println("triple DES");
		
		String str3 = d3.decrypte(cryptedMessage3);
		ArrayList<Integer> decryptedMessage3 = new ArrayList<Integer>();
		for(int k = 0 ; k<cr2Str.length();k++) {
			decryptedMessage3.add(Character.getNumericValue(str3.charAt(k)));
		}
		
		
		String str2 = d2.decrypte(decryptedMessage3);
		ArrayList<Integer> decryptedMessage2 = new ArrayList<Integer>();
		for(int k = 0 ; k<crStr.length();k++) {
			decryptedMessage2.add(Character.getNumericValue(str2.charAt(k)));
		}
		
		System.out.println(d1.decrypte(decryptedMessage2));
	}

}
