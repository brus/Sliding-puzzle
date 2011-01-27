package rs.puzzle.model;

import rs.puzzle.NodeClickListener;
import android.content.Context;
import android.widget.Button;
import android.widget.AbsoluteLayout.LayoutParams;

public class Node extends Button {

	private int nodeSize;
	private int row;
	private int col;
	private int correctPosition;
	private NodeMatrix nodeMatrix;

	public Node(Context context, int nodeSize, int col, int row, String text,
			int correctPosition, NodeMatrix nodeMatrix) {
		super(context);
		setText(text);
		setWidth(nodeSize);
		setHeight(nodeSize);
		this.nodeSize = nodeSize;
		this.col = col;
		this.row = row;
		this.correctPosition = correctPosition;
		this.nodeMatrix = nodeMatrix;
		UpdatePositionOnScreen();
		setOnClickListener(new NodeClickListener());
	}

	public void UpdatePositionOnScreen() {
		setLayoutParams(new LayoutParams(nodeSize, nodeSize, col * nodeSize, row * nodeSize));
	}

	public int getNodeSize() {
		return nodeSize;
	}

	public void setNodeSize(int nodeSize) {
		this.nodeSize = nodeSize;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCorrectPosition() {
		return correctPosition;
	}

	public void setCorrectPosition(int correctPosition) {
		this.correctPosition = correctPosition;
	}

	public NodeMatrix getNodeMatrix() {
		return nodeMatrix;
	}

	public void setNodeMatrix(NodeMatrix nodeMatrix) {
		this.nodeMatrix = nodeMatrix;
	}

}
