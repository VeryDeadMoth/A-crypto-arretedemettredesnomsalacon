package v1;

import java.util.ArrayList;

public class TestDes {

	public static void main(String[] args) {
		DES d = new DES();

		/*int[] wow = d.generePermutation();

		
		d.bitsToString(wow);

		for(int i : wow) {
			System.out.print(i + " ");
		}*/
		
		
		//decalle gauche
		
		ArrayList<Integer> bloc1 = new ArrayList<Integer>();
		int[] b1 = {1,0,1,0,0,1};
		for (int b : b1) {
			bloc1.add(b);
		}
		
		ArrayList<Integer> shiftedBloc1 = d.decalle_gauche(bloc1,2);
		
		System.out.println(bloc1);
		System.out.println(shiftedBloc1);
		
		System.out.println();
		
	}

}
