public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (!board.checkPos(line) || !board.checkPos(column) || !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = color.equals("White") ? 1 : -1;


        if (column == toColumn) {
            if (toLine == line + direction && board.board[toLine][toColumn] == null) {
                return true;
            }
            if ((color.equals("White") && line == 1 || color.equals("Black") && line == 6) && toLine == line + 2 * direction &&
                    board.board[line + direction][column] == null && board.board[toLine][toColumn] == null) {
                return true;
            }
        } else if (Math.abs(column - toColumn) == 1 && toLine == line + direction &&
                board.board[toLine][toColumn] != null && !board.board[toLine][toColumn].getColor().equals(this.color)) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}