import java.util.Random;

public class c5 {
	public static void main(String[] args) {
		ThreadOne th1 = new ThreadOne();
		ThreadTwo th2 = new ThreadTwo();
		ThreadThr th3 = new ThreadThr();
		ThreadFou th4 = new ThreadFou();
		ThreadFiv th5 = new ThreadFiv();
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
	}
}

class ThreadOne extends Thread {
	Random rand = new Random();

	public void run() {
		while (true)
			try {
				this.sleep(rand.nextInt(1));
				System.out.print((char) (rand.nextInt(26) + 'a'));
				// this.wait(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class ThreadTwo extends Thread {
	Random rand = new Random();

	public void run() {
		while (true)
			try {
				this.sleep(rand.nextInt(1));
				System.out.print((char) (rand.nextInt(26) + 'A'));
				// this.wait(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class ThreadThr extends Thread {
	Random rand = new Random();

	public void run() {
		while (true)
			try {
				this.sleep(rand.nextInt(1));
				System.out.print((char) (rand.nextInt(10) + '0'));
				// this.wait(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class ThreadFou extends Thread {
	Random rand = new Random();
	public void run() {
		while (true)
			try {
				this.sleep(rand.nextInt(1));
				System.out.print(" ");
				// this.wait(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class ThreadFiv extends Thread {
	Random rand = new Random();
	public void run() {
		while (true)
			try {
				this.sleep(rand.nextInt(2));
				System.out.print("\n");
				// this.wait(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
