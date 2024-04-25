package academy.pocu.comp3500.assignment3.app;

import academy.pocu.comp3500.assignment3.Player;
import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        testMain();
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
    }

    public static void test10() {
        {
            // pawn captures
            char[][] board = {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 'K', 0, 0, 0, 0, 0},
                    {0, 0, 'R', 'R', 0, 0, 0, 0},
                    {0, 0, 0, 0, 'r', 0, 0, 0},
                    {0, 0, 0, 0, 'r', 'k', 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            printBoard(board);
            Move move = player.getNextMove(board);
            printBoard(makeMove(board, move));


            assert Game.isMoveValid(board, player, move);
//            assert move.fromX == 5;
//            assert move.fromY == 5;
//            assert move.toX == 6;
//            assert move.toY == 4;
        }
    }

    public static void test9() {
        {
            // pawn captures
            char[][] board = {
                    {0, 0, 0, 0, 'K', 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 'Q', 0},
                    {0, 0, 0, 0, 0, 'p', 0, 0},
                    {'k', 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            Move move = player.getNextMove(board);

            assert Game.isMoveValid(board, player, move);
            assert move.fromX == 5;
            assert move.fromY == 5;
            assert move.toX == 6;
            assert move.toY == 4;
        }
    }

    public static void test8() {
        // player captures piece when possible
        {
            char[][] board = {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 'k', 'K', 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            printBoard(board);
            Move move = player.getNextMove(board);
            char[][] newBoard = makeMove(board, move);
            printBoard(newBoard);

            assert Game.isMoveValid(board, player, move);
//            assert move.fromX == 6;
//            assert move.fromY == 5;
//            assert move.toX == 6;
//            assert move.toY == 4;
        }
    }


    public static void test7() {
        // player dodges
        char[][] board = {
                {0, 0, 0, 0, 'K', 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 'R', 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 'R', 0},
                {'k', 0, 0, 0, 0, 'Q', 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        Player player = new Player(true, 10000);

        Move move = player.getNextMove(board);
        System.out.println(move);


        char[][] newBoard = makeMove(board, move);
        printBoard(newBoard);



//        assert Game.isMoveValid(board, player, move);
//        assert move.fromX == 0;
//        assert move.fromY == 6;
//        assert move.toX == 0;
//        assert move.toY == 7;
    }

    public static void test6() {
        // player captures piece when possible
        {
            char[][] board = {
                    {0, 0, 0, 'K', 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 'Q', 0},
                    {0, 0, 0, 0, 0, 0, 'k', 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            printBoard(board);
            Move move = player.getNextMove(board);
//            makeMove(board, move);
            printBoard(makeMove(board, move));

            assert Game.isMoveValid(board, player, move);
            assert move.fromX == 6;
            assert move.fromY == 5;
            assert move.toX == 6;
            assert move.toY == 4;
        }


        {
            // player captures piece when possible
            char[][] board = {
                    {0, 0, 0, 'K', 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 'k', 'Q', 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            Move move = player.getNextMove(board);

            assert Game.isMoveValid(board, player, move);
            assert move.fromX == 5;
            assert move.fromY == 4;
            assert move.toX == 6;
            assert move.toY == 4;
        }

        {
            // player captures piece when there are multiple pieces
            char[][] board = {
                    {0, 0, 0, 'K', 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 'Q', 0},
                    {'k', 0, 0, 0, 0, 'b', 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            Move move = player.getNextMove(board);

            assert Game.isMoveValid(board, player, move);
            assert move.fromX == 5;
            assert move.fromY == 5;
            assert move.toX == 6;
            assert move.toY == 4;
        }
    }

    public static void test5() {
        // player captures piece when possible
        char[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 'K', 0, 0, 0, 0},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {0, 0, 0, 'k', 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        Player player = new Player(false, 10000);
        printBoard(board);

        Move move = player.getNextMove(board);
        char[][] newBoard = makeMove(board, move);
        printBoard(newBoard);


        System.out.printf("!! %d %d %d %d\n", move.fromX, move.fromY, move.toX, move.toY);

        assert Game.isMoveValid(board, player, move);
    }

    public static void test4() {
        // player captures piece when possible
        char[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 'k', 0, 0, 0},
                {'K', 0, 0, 0, 0, 0, 0, 'q'},
        };
        Player player = new Player(true, 10000);
        printBoard(board);

        Move move = player.getNextMove(board);
        char[][] newBoard = makeMove(board, move);
        printBoard(newBoard);

        System.out.printf("!! %d %d %d %d\n", move.fromX, move.fromY, move.toX, move.toY);

        assert Game.isMoveValid(board, player, move);
//        assert move.fromX == 6;
//        assert move.fromY == 5;
//        assert move.toX == 6;
//        assert move.toY == 4;
    }

    public static void test3() {
        {
            // player captures piece when possible
            char[][] board = {
                    {0, 0, 0, 'K', 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 'Q', 0},
                    {0, 0, 0, 0, 0, 0, 'k', 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            Player player = new Player(true, 10000);

            printBoard(board);
            Move move = player.getNextMove(board);
            printBoard(makeMove(board, move));

//            System.out.printf("%d %d %d %d", move.fromX, move.fromY, move.toX, move. toY);

            assert Game.isMoveValid(board, player, move);
            assert move.fromX == 6;
            assert move.fromY == 5;
            assert move.toX == 6;
            assert move.toY == 4;
        }
    }

    public static void test2() {
        char[][] board = {
                {'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 'p', 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        new Position(3, 5);
        Player player = new Player(true, 10000);

        Move move = player.getNextMove(board);
        System.out.printf("%d %d %d %d\n", move.fromX, move.fromY, move.toX, move.toY);
        printBoard(board);
        assert Game.isMoveValid(board, player, move);
    }

    public static void test1() {
        {
            // getNextMove returns a valid move when there is only one piece in board
            char[] symbols = {'k', 'n', 'b', 'r', 'q', 'p'};
            Position[] positions = {new Position(3, 5), new Position(0, 7), new Position(7, 7)};
            for (char s : symbols) {
                for (Position p : positions) {
                    char[][] board = {
                            {'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'},
                            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0},
                    };
                    board[p.y][p.x] = s;
                    Player player = new Player(true, 10000);

                    Move move = player.getNextMove(board);
                    System.out.printf("s:%c, position(x:%d y:%d) %d %d %d %d\n", s, p.x, p.y, move.fromX, move.fromY, move.toX, move.toY);
                    printBoard(board);

                    if (!Game.isMoveValid(board, player, move)) {
                        player.getNextMove(board);
                    }
                    assert Game.isMoveValid(board, player, move);
                }
            }
        }
    }

    public static void testMain() {
        final boolean IS_AUTO_PLAY = true; // true 라면 주기적으로 자동으로 다음 턴이 진행됨; false 라면 Enter/Return 키를 누를 때 진행됨
        final boolean IS_WHITE_KEYBOARD_PLAYER = false; // true 라면 하얀색 플레이어의 수를 콘솔에 입력해야 함
        final boolean IS_BLACK_KEYBOARD_PLAYER = false; // true 라면 검은색 플레이어의 수를 콘솔에 입력해야 함

        final int MAX_MOVE_TIME_MILLISECONDS = 10000; // Player 가 턴마다 수를 결정하는 데에 주어진 시간
        final long AUTO_PLAY_TURN_DURATION_IN_MILLISECONDS = 10000; // Autoplay 중 턴마다 일시중지 되는 기간

        PlayerBase whitePlayer;
        PlayerBase blackPlayer;

        if (IS_WHITE_KEYBOARD_PLAYER) {
            whitePlayer = new KeyboardPlayer(true);
        } else {
            whitePlayer = new Player(true, MAX_MOVE_TIME_MILLISECONDS);
        }
        if (IS_BLACK_KEYBOARD_PLAYER) {
            blackPlayer = new KeyboardPlayer(false);
        } else {
            blackPlayer = new Player(false, MAX_MOVE_TIME_MILLISECONDS);
        }

        Game game = new Game(whitePlayer, blackPlayer, MAX_MOVE_TIME_MILLISECONDS);

        System.out.println("Let the game begin!");
        System.out.println(game.toString());

        for (int i = 0; i < 200; ++i) {
            if (game.isNextTurnWhite() && IS_BLACK_KEYBOARD_PLAYER
                    || !game.isNextTurnWhite() && IS_WHITE_KEYBOARD_PLAYER) {
                if (IS_AUTO_PLAY) {
                    pause(AUTO_PLAY_TURN_DURATION_IN_MILLISECONDS);
                } else {
                    continueOnEnter();
                }
            }

            game.nextTurn();

            clearConsole();
            System.out.println(game.toString());

            if (game.isGameOver()) {
                break;
            }
        }
    }

    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void continueOnEnter() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Press enter to continue:");
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void clearConsole() {
//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void clearConsole() {
        try {
            Map<String, String> env = new HashMap<>();
            env.put("TERM", "xterm");

            ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "clear");
            processBuilder.inheritIO();
            processBuilder.environment().putAll(env);
            processBuilder.start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static String printBoard(final char[][] board) {
        final StringBuilder sb = new StringBuilder(128);

        sb.append("  ");
        for (int x = 0; x < 8; ++x) {
            sb.append((char) (x + 'a'));
        }

        sb.append(System.lineSeparator());

        addHorizontalBorder(sb);

        for (int y = 0; y < 8; ++y) {
            sb.append(y);
            sb.append("|");

            for (int x = 0; x < 8; ++x) {
                char symbol = board[y][x];

                if (symbol == 0) {
                    sb.append(" ");
                } else {
                    sb.append(String.format("%c", board[y][x]));
                }
            }

            sb.append('|');
            sb.append(System.lineSeparator());
        }

        addHorizontalBorder(sb);
        System.out.println(sb.toString());

        return sb.toString();
    }

    private static void addHorizontalBorder(StringBuilder sb) {
        sb.append(' ');
        sb.append('+');
        for (int i = 0; i < 8; ++i) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());
    }


    public static char[][] makeMove(char[][] board, Move move) {
        char[][] newBoard = new char[8][8];
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                newBoard[y][x] = board[y][x];
            }
        }
        // Apply the move
        newBoard[move.toY][move.toX] = newBoard[move.fromY][move.fromX];
        newBoard[move.fromY][move.fromX] = (char) 0;
        return newBoard;
    }
}