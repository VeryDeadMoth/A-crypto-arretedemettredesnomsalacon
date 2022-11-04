package v1;

import java.util.ArrayList;

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
		
	}

}
