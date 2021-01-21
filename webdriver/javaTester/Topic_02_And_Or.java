package javaTester;


public class Topic_02_And_Or {

	public static void main(String[] args) {
		boolean nam = true;
		boolean nu = false;
		
		// Phep AND:
		// Ca 2 deu dung thi moi dung
		nam = true;
		nu = true;
		System.out.println(nam && nu);
		
		// 1 trong 2 sai thi no sai
		nam = false;
		nu = true;
		System.out.println(nam && nu);
		
		// ca 2 deu sai thi no moi sai
		nam = false;
		nu = false;
		System.out.println(nam && nu);
		
		
	}

}
