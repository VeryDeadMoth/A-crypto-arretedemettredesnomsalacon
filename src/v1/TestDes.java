package v1;

public class TestDes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DES d = new DES();
		
		int[] wow = d.stringToBits("hi");
		for(int i : wow) {
			System.out.print(i);
		}
		
	}

}
