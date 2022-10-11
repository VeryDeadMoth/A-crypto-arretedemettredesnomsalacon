package v1;

public class TestDes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DES d = new DES();
		
<<<<<<< Updated upstream
		int[] wow = d.generePermutation();
=======
		
		int[] wow = d.stringToBits("hi");
		
		d.bitsToString(wow);
		
>>>>>>> Stashed changes
		for(int i : wow) {
			System.out.print(i + " ");
		}
		
	}

}
