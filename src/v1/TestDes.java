package v1;

import java.util.ArrayList;
import java.util.List;

public class TestDes {

	public static void main(String[] args) {
		DES d = new DES();
		int[] bitMsg = d.stringToBits("mes couilles sur ton front");
		
		for(int i=0; i<bitMsg.length; i++) {
			if(i%8 == 0) {
				System.out.print(" ");
			}
			System.out.print(bitMsg[i]);
		}

		System.out.println();
		System.out.println(d.bitsToString(bitMsg));
		
		
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
		
		//genere cl�
		System.out.println("genere cl� :");
		d.genereCle(0);
		System.out.println(d.tab_cles.get(0));
		
		//fonction S
		System.out.println("fonction S :");
		ArrayList<Integer> bloc2 = new ArrayList<Integer>(List.of(1,1,0,1,1,1));
		
		System.out.println(d.fonction_S(bloc2));
	}

}
