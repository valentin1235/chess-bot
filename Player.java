package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Player extends PlayerBase {
    private final int WHITE = 1;
    private final int BLACK = -1;
    private final HashMap<Integer, Integer> maxCache = new HashMap<>();
    private final HashMap<Integer, Integer> minCache = new HashMap<>();

    public Player(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }

    @Override
    public Move getNextMove(char[][] board) {
        int depth = 4;
        int player = super.isWhite() ? WHITE : BLACK;

        int[] bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        List<int[]> moves = getPossibleMoves(board, player);
        Stack<Character> stack = new Stack<>();
        for (int[] move : moves) {
            move(board, move, stack);
            if (isGameOver(board)) {
                reverse(board, move, stack);
                return new Move(move[0], move[1], move[2], move[3]);
            }
            int score = minimax(board, depth - 1, false, -player, stack);
            reverse(board, move, stack);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        minCache.clear();
        maxCache.clear();

        return new Move(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
    }

    @Override
    public Move getNextMove(char[][] board, Move opponentMove) {
        int depth = 4;
        int player = super.isWhite() ? WHITE : BLACK;
        int[] bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        Stack<Character> stack = new Stack<>();
        for (int[] move : getPossibleMoves(board, player)) {
            move(board, move, stack);
            if (isGameOver(board)) {
                reverse(board, move, stack);
                return new Move(move[0], move[1], move[2], move[3]);
            }
            int score = minimax(board, depth - 1, false, -player, stack);
            reverse(board, move, stack);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        minCache.clear();
        maxCache.clear();

        return new Move(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
    }

    private int minimax(char[][] board, int depth, boolean isMaximizePlayer, int player, Stack<Character> stack) {
        if (isGameOver(board)) {
            return evaluate(board, player);
        }
        if (depth == 0) {
            return evaluate(board, player);
        }

        if (isMaximizePlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int[] move : getPossibleMoves(board, player)) {
                move(board, move, stack);


                Integer score = maxCache.get(hashBoard(board));
                if (score == null) {
                    if (isGameOver(board)) {
                        maxCache.put(hashBoard(board), Integer.MAX_VALUE - 1);
                        reverse(board, move, stack);
                        return Integer.MAX_VALUE - 1;
                    }
                    score = minimax(board, depth - 1, false, -player, stack);
                    maxCache.put(hashBoard(board), score);

                }
                reverse(board, move, stack);
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int[] move : getPossibleMoves(board, player)) {
                move(board, move, stack);

                Integer score = minCache.get(hashBoard(board));
                if (score == null) {
                    if (isGameOver(board)) {
                        minCache.put(hashBoard(board), Integer.MIN_VALUE + 1);
                        reverse(board, move, stack);
                        return Integer.MIN_VALUE + 1;
                    }

                    score = minimax(board, depth - 1, true, -player, stack);
                    minCache.put(hashBoard(board), score);
                }
                reverse(board, move, stack);
                bestScore = Math.min(bestScore, score);
            }
            return bestScore;
        }
    }



    private List<int[]> getPossibleMoves(char[][] board, int player) {
        List<int[]> moves = new ArrayList<>();
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                char piece = board[y][x];
                if (piece == (char) 0 || (player == WHITE && Character.isUpperCase(piece)) || (player == BLACK && Character.isLowerCase(piece))) {
                    continue;
                }

                switch (Character.toLowerCase(piece)) {
                    case 'p': {
                        int dy = -1 * player;
                        if (y + dy >= 0 && y + dy < 8 && board[y + dy][x] == (char) 0) {
                            moves.add(new int[]{x, y, x, y + dy});
                        }

                        if (y + dy >= 0 && y + dy < 8 && x > 0 && board[y + dy][x - 1] != (char) 0 && isOpponent(player, board[y + dy][x - 1])) {
                            moves.add(new int[]{x, y, x - 1, y + dy});
                        }
                        if (y + dy >= 0 && y + dy < 8 && x < 7 && board[y + dy][x + 1] != (char) 0 && isOpponent(player, board[y + dy][x + 1])) {
                            moves.add(new int[]{x, y, x + 1, y + dy});
                        }
                        break;
                    }

                    case 'r': {
                        for (int dy = -1; dy <= 1; dy += 2) {
                            for (int ny = y + dy; ny >= 0 && ny < 8; ny += dy) {
                                if (ny == y) {
                                    continue;
                                }

                                if (board[ny][x] == (char) 0 && isRookValid(x, y, 0, ny, -1 * dy, board)) {
                                    moves.add(new int[]{x, y, x, ny});
                                } else if (isOpponent(player, board[ny][x]) && isRookValid(x, y, 0, ny, -1 * dy, board)) {
                                    moves.add(new int[]{x, y, x, ny});
                                }
                            }
                        }
                        for (int dx = -1; dx <= 1; dx += 2) {
                            for (int nx = x + dx; nx >= 0 && nx < 8; nx += dx) {
                                if (nx == x) {
                                    continue;
                                }
                                if (board[y][nx] == (char) 0 && isRookValid(x, y, nx, 0, -1 * dx, board)) {
                                    moves.add(new int[]{x, y, nx, y});
                                } else if (isOpponent(player, board[y][nx]) && isRookValid(x, y, nx, 0, -1 * dx, board)) {
                                    moves.add(new int[]{x, y, nx, y});
                                }
                            }
                        }
                        break;
                    }
                    case 'b': {
                        for (int dx = -1; dx <= 1; dx += 2) {
                            for (int dy = -1; dy <= 1; dy += 2) {
                                for (int i = 1; i < 8; i++) {
                                    int nx = x + i * dx;
                                    int ny = y + i * dy;
                                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
                                        continue;
                                    }

                                    if (isOpponent(player, board[ny][nx]) && isBishopValid(x, y, -1 * dx, -1 * dy, i, board)) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    } else if (board[ny][nx] == (char) 0 && isBishopValid(x, y, -1 * dx, -1 * dy, i, board)) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    }
                                }
                            }
                        }
                        break;
                    }

                    case 'n': {
                        for (int dx : new int[]{-1, 1}) {
                            for (int dy : new int[]{-2, 2}) {
                                int nx = x + dx;
                                int ny = y + dy;
                                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                                    if (isOpponent(player, board[ny][nx])) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    } else if (board[ny][nx] == (char) 0) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    }
                                }
                            }
                        }

                        for (int dx : new int[]{-2, 2}) {
                            for (int dy : new int[]{-1, 1}) {
                                int nx = x + dx;
                                int ny = y + dy;
                                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                                    if (isOpponent(player, board[ny][nx])) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    } else if (board[ny][nx] == (char) 0) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 'q': {
                        for (int d : new int[]{-1, 1}) {
                            for (int i = 1; i < 8; i++) {
                                int nx = x + i * d;
                                if (nx < 0 || nx >= 8) {
                                    continue;
                                }
                                if (board[y][nx] == (char) 0 && isRookValid(x, y, nx, 0, -1 * d, board)) {
                                    moves.add(new int[]{x, y, nx, y});
                                } else if (isOpponent(player, board[y][nx]) && isRookValid(x, y, nx, 0, -1 * d, board)) {
                                    moves.add(new int[]{x, y, nx, y});
                                }
                            }
                            for (int i = 1; i < 8; i++) {
                                int ny = y + i * d;
                                if (ny < 0 || ny >= 8) {
                                    continue;
                                }
                                if (board[ny][x] == (char) 0 && isRookValid(x, y, 0, ny, -1 * d, board)) {
                                    moves.add(new int[]{x, y, x, ny});
                                } else if (isOpponent(player, board[ny][x]) && isRookValid(x, y, 0, ny, -1 * d, board)) {
                                    moves.add(new int[]{x, y, x, ny});
                                }
                            }
                        }

                        for (int dx = -1; dx <= 1; dx += 2) {
                            for (int dy = -1; dy <= 1; dy += 2) {
                                for (int i = 1; i < 8; i++) {
                                    int nx = x + i * dx;
                                    int ny = y + i * dy;
                                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
                                        continue;
                                    }
                                    if (board[ny][nx] == (char) 0 && isBishopValid(x, y, -1 * dx, -1 * dy, i, board)) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    } else if (isOpponent(player, board[ny][nx]) && isBishopValid(x, y, -1 * dx, -1 * dy, i, board)) {
                                        moves.add(new int[]{x, y, nx, ny});
                                    }
                                }
                            }
                        }
                    }
                    case 'k': {
                        for (int dx = -1; dx <= 1; ++dx) {
                            for (int dy = -1; dy <= 1; ++dy) {
                                if (dx == 0 && dy == 0) {
                                    continue;
                                }
                                int nx = x + dx;
                                int ny = y + dy;
                                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
                                    continue;
                                }
                                if (isOpponent(player, board[ny][nx])) {
                                    moves.add(new int[]{x, y, nx, ny});
                                }
                                if (board[ny][nx] == (char) 0) {
                                    moves.add(new int[]{x, y, nx, ny});
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

        return moves;
    }



    private boolean isGameOver(char[][] board) {
        boolean whiteHasMaterial = false;
        boolean blackHasMaterial = false;
        boolean kingCaptured = true;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char p = board[i][j];
                if (p == 'k' || p == 'K') {
                    kingCaptured = false;
                }
                if (p == 'K' || p == 'Q' || p == 'R' || p == 'B' || p == 'N' || p == 'P') {
                    whiteHasMaterial = true;
                } else if (p == 'k' || p == 'q' || p == 'r' || p == 'b' || p == 'n' || p == 'p') {
                    blackHasMaterial = true;
                }
            }
        }

        return !whiteHasMaterial || !blackHasMaterial || kingCaptured;
    }




    private int evaluate(char[][] board, int player) {
        final int KING_VALUE = 200;
        final int QUEEN_VALUE = 9;
        final int ROOK_VALUE = 5;
        final int KNIGHT_VALUE = 3;
        final int BISHOP_VALUE = 3;
        final int PAWN_VALUE = 1;

        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char piece = board[i][j];
                if (piece == (char) 0) {
                    continue;
                }
                int value = 0;
                switch (piece) {
                    case 'K':
                        value = -KING_VALUE;
                        break;
                    case 'Q':
                        value = -QUEEN_VALUE;
                        break;
                    case 'R':
                        value = -ROOK_VALUE;
                        break;
                    case 'N':
                        value = -KNIGHT_VALUE;
                        break;
                    case 'B':
                        value = -BISHOP_VALUE;
                        break;
                    case 'P':
                        value = -PAWN_VALUE;
                        break;
                    case 'k':
                        value = KING_VALUE;
                        break;
                    case 'q':
                        value = QUEEN_VALUE;
                        break;
                    case 'r':
                        value = ROOK_VALUE;
                        break;
                    case 'n':
                        value = KNIGHT_VALUE;
                        break;
                    case 'b':
                        value = BISHOP_VALUE;
                        break;
                    case 'p':
                        value = PAWN_VALUE;
                        break;
                }
                score += (player == WHITE ? value : -value);
            }
        }
        return score;
    }

    private int hashBoard(char[][] board) {
        if (board == null)
            return 0;

        int result = 1;

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char element = board[i][j];
                result = 31 * result + Character.hashCode(element);
            }
        }

        return result;
    }

    private boolean isBishopValid(int x, int y, int dx, int dy, int i, char[][] board) {
        int nx = x + -1 * i * dx;
        int ny = y + -1 * i * dy;

        nx += dx;
        ny += dy;

        while (x != nx && y != ny) {
            if (board[ny][nx] != (char) 0) {
                return false;
            }

            nx += dx;
            ny += dy;
        }

        return true;
    }

    private boolean isRookValid(int x, int y, int nx, int ny, int i, char[][] board) {
        if (nx == 0 && ny != y) {
            int dy = ny;

            dy += i;
            while (y != dy) {
                if (board[dy][x] != (char) 0) {
                    return false;
                }

                dy += i;
            }
        }

        if (ny == 0 && nx != x) {
            int dx = nx;

            dx += i;
            while (x != dx) {
                if (board[y][dx] != (char) 0) {
                    return false;
                }

                dx += i;
            }
        }

        return true;
    }

    public boolean isOpponent(int player, char piece) {
        if (piece == (char) 0) {
            return false;
        }
        if (player == 1 && Character.isLowerCase(piece)) {
            return false;
        }

        if (player == -1 && Character.isUpperCase(piece)) {
            return false;
        }

        return true;
    }


    public void move(char[][] board, int[] move, Stack<Character> stack) {
        Character captured = board[move[3]][move[2]];

        board[move[3]][move[2]] = board[move[1]][move[0]];
        board[move[1]][move[0]] = (char) 0;

        stack.push(captured);
    }

    public void reverse(char[][] board, int[] move, Stack<Character> stack) {
        board[move[1]][move[0]] = board[move[3]][move[2]];
        board[move[3]][move[2]] = stack.pop();
    }
}