public class KnightBoard {
  private int[][]board;

  @throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
    if ((startingRows <= 0) || (startingCols <= 0)) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    removeNull();
  }

  public void removeNull() {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
	       board[a][b] = 0;
      }
    }
  }

  public String toString(){
    String s = "";
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
        if (board[a][b] == 0) {
          s += "  _";
        }
        if (board.length * board[0].length < 10) {
          s += "  " + board[a][b];
        }
        else {
          if ((board[a][b] + "").length() > 1) {
            s += " " + board[a][b];
          }
          else {
            s += "  " + board[a][b];
          }
        }
      }
      s += "\n";
    }
    return s;
  }

  private boolean checkZero() {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
        if (board[a][b] != 0) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean solve(int startingRow, int startingCol) {
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    if ((startingRows < 0) || (startingCols < 0) || (startingRows >= board.length) || (startingCols >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    return solveH(startingRow, startingCol, 1);
  }

  public boolean solveH(int staRow, int staCol, int level) {
    if (curNum == ((staRow * staCol) + 1)) {
      return true;
    }
    if (placeKnight(staRow, staCol, level)) {
      if (solveH(staRow + 2, staCol + 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 2, staCol - 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 1, staCol + 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 1, staCol + 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 2, staCol + 1, level + 1)) {
	return true;
      }
      if (solveH(staRow - 2, staCol - 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 1, staCol - 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 1, staCol - 2, level + 1)) {
	return true;
      }
      removeKnight(staRow, staCol);
    }
    return false;
  }

  public boolean placeKnight(int staRow, int staCol, int level) {
    if (board[staRow][staCol] != 0) {
      return false;
    }
    board[staRow][staCol] = level;
    return true;
  }

  public boolean removeKnight(int staRow, int staCol) {
    if (board[staRow][staCol] != 0) {
      board[staRow][staCol] = 0;
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5, 5);
    System.out.println(k.solve(0, 0));
  }


}
