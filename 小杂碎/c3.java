import java.util.Random;

public class game3 {
	public static void main(String[] args) {
		Random rand = new Random();
		int ji=0,ou=0;
		int ran;
		for(int i=0;i<2200000000;i++){
			ran=rand.nextInt(100);
			if(ran>=75)
				ji++;
			else
				ou++;
		//	System.out.println(ran);
		}
		System.out.println("ÆæÊı "+ji+"     Å¼Êı "+ou);
	}
}
