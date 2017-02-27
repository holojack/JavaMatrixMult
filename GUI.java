import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {

	public int[][] A;
	public int[][] B;
	public int[][] C;
	private String windowTitle = "CS 372 Final Project";
	private JFrame mainWindow;
	private JTextField ALength;
	private JTextField AWidth;
	private JTextField BLength;
	private JTextField BWidth;
	private JTextArea ErrorPlace;
	private JButton updateButton;
	private Container container;

	public static void main(String[] args) {
		GUI gui = new GUI();

		gui.InitGUI();
	}

	public void InitGUI() {
		mainWindow = new JFrame(windowTitle);

		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ALength = new JTextField();
		ALength.setEditable(true);
		ALength.setBorder(BorderFactory.createTitledBorder("First matrix length:"));

		AWidth = new JTextField();
		AWidth.setEditable(true);
		AWidth.setBorder(BorderFactory.createTitledBorder("First matrix width:"));

		BLength = new JTextField();
		BLength.setEditable(true);
		BLength.setBorder(BorderFactory.createTitledBorder("Second matrix width:"));

		BWidth = new JTextField();
		BWidth.setEditable(true);
		BWidth.setBorder(BorderFactory.createTitledBorder("Second matrix width:"));

		ErrorPlace = new JTextArea();
		ErrorPlace.setEditable(false);
		ErrorPlace.setText("0");

		updateButton = new JButton("Next");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AWidth.getText() != null && BWidth.getText() != null && ALength.getText() != null
						&& BLength.getText() != null) {
					if (!AWidth.getText().equals("") && !BWidth.getText().equals("") && !ALength.getText().equals("")
							&& !BLength.getText().equals("")) {
						if (!AWidth.getText().equals(BLength.getText())) {
							ErrorPlace.setText("A width must match B length");
						} else {
							try {
								A = new int[Integer.parseInt(ALength.getText())][Integer.parseInt(AWidth.getText())];
								B = new int[Integer.parseInt(BLength.getText())][Integer.parseInt(BWidth.getText())];
								C = new int[A.length][B[0].length];
								ShowMatrices();
							} catch (Exception ex) {
								ErrorPlace.setText("Please only place integers in matrix cells.");
								return;
							}
						}
					} else {
						ErrorPlace.setText("Please fill both grids with integers.");
					}
				} else {
					ErrorPlace.setText("Please fill both grids with integers.");
				}
			}
		});

		container = mainWindow.getContentPane();
		container.setLayout(new BorderLayout(6, 6));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(ALength);
		panel.add(AWidth);
		panel.add(BLength);
		panel.add(BWidth);

		container.add(panel, BorderLayout.CENTER);
		container.add(updateButton, BorderLayout.EAST);
		container.add(ErrorPlace, BorderLayout.SOUTH);

		mainWindow.pack();
		mainWindow.setSize(400, 300);
		mainWindow.setVisible(true);
	}

	public void ShowMatrices() {
		JLabel AMatrixLabel = new JLabel("A Matrix cells:");
		JTextField[][] AFields = new JTextField[A.length][A[0].length];
		JPanel AGrid = new JPanel();
		AGrid.setLayout(new GridLayout(A.length, A[0].length));

		for (int i = 0; i < AFields.length; i++) {
			for (int j = 0; j < AFields[0].length; j++) {
				AFields[i][j] = new JTextField();
				AFields[i][j].setEditable(true);
				AGrid.add(AFields[i][j]);
			}
		}

		JPanel APanel = new JPanel();
		APanel.setLayout(new BorderLayout(0, 0));
		APanel.add(AMatrixLabel, BorderLayout.NORTH);
		APanel.add(AGrid, BorderLayout.CENTER);

		JLabel BMatrixLabel = new JLabel("B Matrix cells:");
		JTextField[][] BFields = new JTextField[B.length][B[0].length];
		JPanel BGrid = new JPanel();
		BGrid.setLayout(new GridLayout(B.length, B[0].length));

		for (int i = 0; i < BFields.length; i++) {
			for (int j = 0; j < BFields[0].length; j++) {
				BFields[i][j] = new JTextField();
				BFields[i][j].setEditable(true);
				BGrid.add(BFields[i][j]);
			}
		}

		JPanel BPanel = new JPanel();
		BPanel.setLayout(new BorderLayout(0, 0));
		BPanel.add(BMatrixLabel, BorderLayout.NORTH);
		BPanel.add(BGrid, BorderLayout.CENTER);

		JTextArea ErrorSpot = new JTextArea();
		ErrorSpot.setEditable(false);
		ErrorSpot.setText("");

		JButton runButton = new JButton("Calculate");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < AFields.length; i++) {
					for (int j = 0; j < AFields[0].length; j++) {
						if (AFields[i][j].getText() != null) {
							if (!AFields[i][j].getText().equals("")) {
								try {
									A[i][j] = Integer.parseInt(AFields[i][j].getText());
								} catch (Exception ex) {
									ErrorSpot.setText("All entries must be integers. Try again.");
									return;
								}
							} else {
								ErrorSpot.setText("All entries must be integers. Try again.");
							}
						} else {
							ErrorSpot.setText("All entries must be integers. Try again.");
						}
					}
				}

				for (int i = 0; i < BFields.length; i++) {
					for (int j = 0; j < BFields[0].length; j++) {
						if (BFields[i][j].getText() != null) {
							if (!BFields[i][j].getText().equals("")) {
								try {
									B[i][j] = Integer.parseInt(BFields[i][j].getText());
								} catch (Exception ex) {
									ErrorSpot.setText("All entries must be integers. Try again.");
									return;
								}
							} else {
								ErrorSpot.setText("All entries must be integers. Try again.");
							}
						} else {
							ErrorSpot.setText("All entries must be integers. Try again.");
						}
					}
				}

				calculate();
				showResult();
			}
		});

		JFrame matrixWindow = new JFrame(windowTitle);

		Container matrixContainer = matrixWindow.getContentPane();
		matrixContainer.setLayout(new BorderLayout(6, 6));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(APanel);
		panel.add(BPanel);

		matrixContainer.add(panel, BorderLayout.CENTER);
		matrixContainer.add(runButton, BorderLayout.SOUTH);
		matrixContainer.add(ErrorSpot, BorderLayout.NORTH);

		matrixWindow.pack();
		matrixWindow.setSize(A.length * 100, B.length * 100);
		matrixWindow.setVisible(true);
	}

	private void demo() {
		A = new int[3][3];
		B = new int[3][3];

		A[0][0] = 3;
		A[0][1] = 1;
		A[0][2] = 4;
		A[1][0] = 5;
		A[1][1] = 2;
		A[1][2] = 0;
		A[2][0] = 2;
		A[2][1] = 3;
		A[2][2] = 6;

		B[0][0] = 0;
		B[0][1] = 1;
		B[0][2] = 1;
		B[1][0] = 4;
		B[1][1] = 5;
		B[1][2] = 3;
		B[2][0] = 2;
		B[2][1] = 2;
		B[2][2] = 7;
	}

	private void calculate() {
		Runner runner = new Runner(A, B, C);
		runner.run();
	}

	private void showResult() {
		JFrame finishFrame = new JFrame("Result");

		Container finishContent = finishFrame.getContentPane();
		finishContent.setLayout(new GridLayout(C.length, C[0].length));

		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[0].length; j++) {
				finishContent.add(new JLabel("" + C[i][j]));
			}
		}

		finishFrame.pack();
		finishFrame.setSize(300, 300);
		finishFrame.setVisible(true);
	}
}
