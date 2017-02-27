import java.util.ArrayList;

public class Runner {

	private int[][] A;
	private int[][] B;
	private int[][] C;
	
	public Runner(int[][] A, int [][] B, int[][] C) {
		this.A = A;
		this.B = B;
		this.C = C;
	}
	
	public void run(){
		ArrayList<Thread> threads = new ArrayList<>();
		for(int i = 0; i < C.length ; i ++) {
			for(int j = 0 ; j < C[0].length; j++) {
				Thread th = new Thread(new WorkerThread(i,j,A,B,C));
				threads.add(th);
				th.run();
			}
		}
		
		for(Thread x : threads) {
			try {
				x.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
