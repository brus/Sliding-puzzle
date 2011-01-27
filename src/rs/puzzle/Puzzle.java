package rs.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rs.puzzle.model.Node;
import rs.puzzle.model.NodeMatrix;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AbsoluteLayout.LayoutParams;

public class Puzzle extends Activity {

	private NodeMatrix nodeMatrix;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showStartGameScreen();
	}

	private void showStartGameScreen() {
		setContentView(R.layout.main);
		AddStartButtonOnClickListener();
	}

	private void AddStartButtonOnClickListener() {
		Button btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText edtCols = (EditText) findViewById(R.id.edtCols);
				EditText edtRows = (EditText) findViewById(R.id.edtRows);
				int rows = Integer.valueOf(edtRows.getText().toString());
				int cols = Integer.valueOf(edtCols.getText().toString());
				ViewGroup layout = (ViewGroup) findViewById(R.id.mainLayout);
				Integer nodeSize = Math.min(layout.getWidth() / cols, (layout
						.getHeight() - 60)
						/ rows);
				CreatePuzzle(rows, cols, nodeSize);
				AddNewGameButton(rows, nodeSize);
			}
		});
	}

	private void AddNewGameButton(int rows, int nodeSize) {
		ViewGroup layout = (ViewGroup) findViewById(R.id.mainLayout);
		Button button = new Button(this);
		button.setText("Start new game");
		button.setLayoutParams(new LayoutParams(160, 40, (layout.getWidth() - 160) / 2, rows * nodeSize + 10));
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showStartGameScreen();
			}
		});
		layout.addView(button);
	}

	protected void CreatePuzzle(int rows, int cols, int nodeSize) {
		ViewGroup layout = (ViewGroup) findViewById(R.id.mainLayout);
		layout.removeAllViews();
		nodeMatrix = new NodeMatrix(rows, cols, nodeSize, this);

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if ((row != rows - 1) || (col != cols - 1)) {
					Node node = new Node(this, nodeSize, col, row, String
							.valueOf(row * cols + col + 1), row * cols + col,
							nodeMatrix);
					layout.addView(node);
					node.UpdatePositionOnScreen();
					nodeMatrix.getMatrix()[row][col] = node;
				}
			}
		}
		ShufflePuzzle();
	}

	private void ShufflePuzzle() {
		List<Node> nodeArray = createShuffleArray();
		Random generator = new Random();
		for (int i = 0; i < 1000; i++) {
			int index = generator.nextInt(nodeArray.size());
			if (index < nodeArray.size() - 1) {
				Node firstNode = nodeArray.get(index);
				Node secondNode = nodeArray.get(index + 1);
				nodeMatrix.SwapPositions(firstNode.getRow(),
						firstNode.getCol(), secondNode.getRow(), secondNode
								.getCol());
				nodeArray.set(index, secondNode);
				nodeArray.set(index + 1, firstNode);
			} else {
				Node firstNode = nodeArray.get(index);
				Node secondNode = nodeArray.get(index - 1);
				nodeMatrix.SwapPositions(firstNode.getRow(),
						firstNode.getCol(), secondNode.getRow(), secondNode
								.getCol());
				nodeArray.set(index, secondNode);
				nodeArray.set(index - 1, firstNode);
			}
		}
	}

	private List<Node> createShuffleArray() {
		List<Node> nodeArray = new ArrayList<Node>();
		for (int row = 0; row < nodeMatrix.getRows(); row++) {
			if (row % 2 == 0) {
				for (int col = 0; col < nodeMatrix.getCols(); col++) {
					if (nodeMatrix.getMatrix()[row][col] != null) {
						nodeArray.add(nodeMatrix.getMatrix()[row][col]);
					}
				}
			} else {
				for (int col = nodeMatrix.getCols() - 1; col >= 0; col--) {
					if (nodeMatrix.getMatrix()[row][col] != null) {
						nodeArray.add(nodeMatrix.getMatrix()[row][col]);
					}
				}
			}
		}
		return nodeArray;
	}

	public void ShowPuzzleSolvedMessage() {
		setContentView(R.layout.success_layout);
		addPlayAnotherClickListener();
		addExitClickListener();
	}

	private void addPlayAnotherClickListener() {
		Button button = (Button) findViewById(R.id.btnPlayAnother);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showStartGameScreen();
			}
		});
	}

	private void addExitClickListener() {
		Button button = (Button) findViewById(R.id.btnExit);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});		
	}

}