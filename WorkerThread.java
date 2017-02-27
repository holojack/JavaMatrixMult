
public class WorkerThread implements Runnable{
	
	private int row;
	private int col;
	private int[][] A;
	private int[][] B;
	private int[][] C;
	
	public WorkerThread(int row, int col, int[][] A, int [][] B, int[][] C) {
		this.row = row;
		this.col = col;
		this.A = A;
		this.B = B;
		this.C = C;
	}

	@Override
	public void run() {
		C[row][col] = 0;
		
		for(int i = 0; i < A[0].length ; i++) {
			C[row][col] += A[row][i] * B[i][col];
		}
	}
}
