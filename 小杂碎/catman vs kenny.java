import java.util.Random;

public class game2 {
	public static void main(String[] args) {
		System.out.println("\n\n\n\n\n\n\n\n\n\nCartman vs Kenny");
		int hp_Cartman = 100000, hp_Kenny = 100000;
		Random rand = new Random();
		while (hp_Cartman > 0 && hp_Kenny > 0) {
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int roll = rand.nextInt(1999) ;
			System.out.println("这次roll出了 " + roll);
			if (roll % 2 == 0) {
				hp_Cartman -= roll / 2;
				System.out.println("\n\nCartman扣除血量" + roll / 2);
			} else {
				hp_Kenny -= roll / 2;
				System.out.println("\n\nKennys扣除血量" + roll / 2);
			}
			System.out.println("\nCartman血量   " + hp_Cartman + "\tKenny血量"+ hp_Kenny+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
		if (hp_Cartman <= 0)
			System.out.println("\n\n\n\n\n\n\n\n\n\nKenny获胜");
		if (hp_Kenny <= 0)
			System.out.println("\n\n\n\n\n\n\n\n\n\nCartman获胜");
	}
}
