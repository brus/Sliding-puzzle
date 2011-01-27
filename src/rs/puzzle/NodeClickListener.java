package rs.puzzle;

import rs.puzzle.model.Node;
import android.view.View;
import android.view.View.OnClickListener;

public class NodeClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		Node node = (Node) v;
		boolean moveDone = false;
		if (!moveDone){
			moveDone = MoveLeft(node);
		}
		if (!moveDone){
			moveDone = MoveRight(node);
		}
		if (!moveDone){
			moveDone = MoveUp(node);
		}
		if (!moveDone){
			moveDone = MoveDown(node);
		}
		node.UpdatePositionOnScreen();
		node.getNodeMatrix().checkIsPuzzleSolved();
	}

	private boolean MoveLeft(Node node) {
		boolean moveDone = false;
		if (node.getCol() > 0){
			int nodesToMove = GetNumberOfNodesToMoveLeft(node);
			for (int i = nodesToMove; i > 0; i--){
				node.getNodeMatrix().SwapPositions(node.getRow(), node.getCol() - i + 1, node.getRow(), node.getCol() - i);
				moveDone = true;
			}
		}
		return moveDone;
	}
	
	private int GetNumberOfNodesToMoveLeft(Node node) {
		for (int i = node.getCol() - 1; i >= 0; i--){
			if (node.getNodeMatrix().getMatrix()[node.getRow()][i] == null){
				return node.getCol() - i;
			}
		}
		return 0;
	}

	private boolean MoveRight(Node node) {
		boolean moveDone = false;
		if (node.getCol() < node.getNodeMatrix().getMatrix()[0].length-1){
			int nodesToMove = GetNumberOfNodesToMoveRight(node);
			for (int i = nodesToMove; i > 0; i--){
				node.getNodeMatrix().SwapPositions(node.getRow(), node.getCol() + i - 1, node.getRow(), node.getCol() + i);
				moveDone = true;
			}
		}
		return moveDone;
	}
	
	private int GetNumberOfNodesToMoveRight(Node node) {
		for (int i = node.getCol() + 1; i < node.getNodeMatrix().getMatrix()[0].length; i++){
			if (node.getNodeMatrix().getMatrix()[node.getRow()][i] == null){
				return i - node.getCol();
			}
		}
		return 0;
	}

	private boolean MoveUp(Node node) {
		boolean moveDone = false;
		if (node.getRow() > 0){
			int nodesToMove = GetNumberOfNodesToMoveUp(node);
			for (int i = nodesToMove; i > 0; i--){
				node.getNodeMatrix().SwapPositions(node.getRow() - i + 1, node.getCol(), node.getRow() - i, node.getCol());
				moveDone = true;
			}
		}
		return moveDone;
	}
	
	private int GetNumberOfNodesToMoveUp(Node node) {
		for (int i = node.getRow() - 1; i >= 0; i--){
			if (node.getNodeMatrix().getMatrix()[i][node.getCol()] == null){
				return node.getRow() - i;
			}
		}
		return 0;
	}

	private boolean MoveDown(Node node) {
		boolean moveDone = false;
		if (node.getRow() < node.getNodeMatrix().getMatrix().length-1){
			int nodesToMove = GetNumberOfNodesToMoveDown(node);
			for (int i = nodesToMove; i > 0; i--){
				node.getNodeMatrix().SwapPositions(node.getRow() + i - 1, node.getCol(), node.getRow() + i, node.getCol());
				moveDone = true;
			}
		}
		return moveDone;
	}

	private int GetNumberOfNodesToMoveDown(Node node) {
		for (int i = node.getRow() + 1; i < node.getNodeMatrix().getMatrix().length; i++){
			if (node.getNodeMatrix().getMatrix()[i][node.getCol()] == null){
				return i - node.getRow();
			}
		}
		return 0;
	}

}
