package rs.puzzle.model;

import rs.puzzle.Puzzle;

public class NodeMatrix {

	private int rows;
	private int cols;
	private int nodeSize;
	private Node[][] matrix;
	private Puzzle puzzle;

	public NodeMatrix(int rows, int cols, int nodeSize, Puzzle puzzle) {
		this.rows = rows;
		this.cols = cols;
		this.nodeSize = nodeSize;
		this.matrix = new Node[rows][cols];
		this.puzzle = puzzle;
	}

	public void SwapPositions(int row1, int col1, int row2, int col2) {
		Node tempNode = matrix[row1][col1];
		MoveNodeToPosition(matrix[row2][col2], row1, col1);
		MoveNodeToPosition(tempNode, row2, col2);
	}

	public void MoveNodeToPosition(Node node, int row, int col) {
		matrix[row][col] = node;
		if (node != null) {
			node.setRow(row);
			node.setCol(col);
			node.UpdatePositionOnScreen();
		}
	}

	public void checkIsPuzzleSolved() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if ((row != rows - 1) || (col != cols - 1)) {
					if ((matrix[row][col] == null) || (row * cols + col != matrix[row][col].getCorrectPosition())) {
						return;
					}
				}
			}
		}
		puzzle.ShowPuzzleSolvedMessage();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getNodeSize() {
		return nodeSize;
	}

	public void setNodeSize(int nodeSize) {
		this.nodeSize = nodeSize;
	}

	public Node[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Node[][] matrix) {
		this.matrix = matrix;
	}

}
