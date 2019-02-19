public class KnightBoard {
  private int[][]board;
  private int count;

  //@throws IllegalArgumentException when either parameter is negative;
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
        else if (board.length * board[0].length < 10) {
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
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    return solveH(startingRow, startingCol, 1);
  }

  public boolean solveH(int staRow, int staCol, int level) {
    if (level == ((board.length * board[0].length) + 1)) {
      return true;
    }
    if ((staRow >= board.length) || (staCol >= board[0].length) || (staRow < 0) || (staCol < 0)) {
      return false;
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

  public int countSolutions(int startingRow, int startingCol) {
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    if ((startingRow > board.length) || (startingCol > board[0].length)) {
      return countH(startingRow, startingCol, 1);
    }
    for (int a = startingRow; a < board.length; a++) {
      for (int b = startingCol; b < board[0].length; b++) {
	count = count + count(a, b);
      }
    }
    return count;
  }

  public int countH(int staRow, int staCol, int level) {
    if (level == ((board.length * board[0].length) + 1)) {
      return 1;
    }
    if ((staRow >= board.length) || (staCol >= board[0].length) || (staRow < 0) || (staCol < 0)) {
      return 0;
    }
    if (placeKnight(staRow, staCol, level)) {
      count = count + countH(staRow + 2, staCol + 1, level + 1);
      count = count + countH(staRow + 2, staCol - 1, level + 1);
      count = count + countH(staRow + 1, staCol + 2, level + 1);
      count = count + countH(staRow - 1, staCol + 2, level + 1);
      count = count + countH(staRow - 2, staCol + 1, level + 1);
      count = count + countH(staRow - 2, staCol - 1, level + 1);
      count = count + countH(staRow + 1, staCol - 2, level + 1);
      count = count + countH(staRow - 1, staCol - 2, level + 1);
      removeKnight(staRow, staCol);
    }
    return count;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5, 5);
    System.out.println(k);
    System.out.println(k.solve(0, 0));
    System.out.println(k);
    KnightBoard a = new KnightBoard(6, 6);
    System.out.println(a.solve(0, 0));
    System.out.println(a);
    KnightBoard ab = new KnightBoard(6, 7);
    System.out.println(ab.solve(0, 0));
    System.out.println(ab);
  }


}
