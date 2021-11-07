import java.util.*;

/**
 * Moves: UP, DOWN, STAY
 */
enum Move {
    UP,
    DOWN,
    STAY
}

//main class
public class UpAndDown {

    /**
     * Compute the moves from the input map
     *
     * @param map
     * @return list of moves (UP, DOWN, STAY)
     */
    public static List<Move> compute(String map) throws IllegalArgumentException {
        //last row in the map not ended with \n so we should add + 1 to rowCount;
        int rowCount = (int) map.chars().filter(c -> c == '\n').count() + 1;
        int columnCount = map.indexOf("\n");

        List<Move> moves = new ArrayList<>();

        char[][] mapCharactersArray = new char[rowCount][columnCount];
        int row = 0;
        int column = 0;
        for (int i = 0; i < map.length(); i++) {
            if (map.charAt(i) == '\n') {
                row++;
            } else {
                //there should be even number of columns
                if (row >= rowCount || column >= columnCount || columnCount % 2 != 0)
                    throw new IllegalArgumentException("Invalid input map");
                mapCharactersArray[row][column] = map.charAt(i);
            }
            if (column == columnCount) column = 0;
            else column++;
        }

        collectMoves(rowCount, columnCount, moves, mapCharactersArray);
        return moves;
    }

    /**
     * Collects the moves from the character array of the input map
     *
     * @param rowCount
     * @param columnCount
     * @param moves
     * @param mapCharactersArray
     */
    private static void collectMoves(long rowCount, int columnCount, List<Move> moves, char[][] mapCharactersArray) {
        // we should step with 2 characters as ever move is 2 characters length
        for (int column = 0; column < columnCount; column = column + 2) {
            for (int row = 0; row < rowCount; row++) {
                if (mapCharactersArray[row][column] == '_' && mapCharactersArray[row][column + 1] == '_') {
                    moves.add(Move.STAY);
                } else if (mapCharactersArray[row][column] == '_' && mapCharactersArray[row][column + 1] == '|') {
                    moves.add(Move.UP);
                } else if (mapCharactersArray[row][column] == '_' && mapCharactersArray[row + 1][column + 1] == '|') {
                    moves.add(Move.DOWN);
                }
            }
        }
    }

    /**
     * Prints out the input map and output movements just to see if it mapped correctly
     *
     * @param map       the input map
     * @param movements the first letter out movements U - UP, D - DOWN or S - STAY
     */
    private static void printRawInputOutput(String map, List<Move> movements) {
        System.out.println(map);
        movements.forEach(m -> System.out.print(m.toString().charAt(0) + " "));
    }

    /**
     * Prints out the moves statistics
     *
     * @param moves list of moves: UP, DOWN, STAY
     */
    private static void printOutputStatistics(List<Move> moves) {
        System.out.println("\n\n==== Moves statistics ====");
        System.out.println("\nMoves: " + moves);
        Long upwards = moves.stream().filter(m -> m == Move.UP).count();
        Long downwards = moves.stream().filter(m -> m == Move.DOWN).count();
        Long stay = moves.stream().filter(m -> m == Move.STAY).count();
        System.out.println("Moves upwards: " + upwards);
        System.out.println("Moves downwards: " + downwards);
        System.out.println("Moves same level (stay): " + stay);
        int elevation = (int) (upwards - downwards);
        System.out.println("Total elevation hiked: " + elevation);
    }

    public static void main(String[] args) {
        // in map 2 characters define a move
        // _| means UP
        // _
        //  | means DOWN
        // __ means STAY
        String map =
                "                                              \n" +
                        "                              _               \n" +
                        "                            _| |_             \n" +
                        "                          _|     |_       _   \n" +
                        "      _   _       _______|         |_   _| |_ \n" +
                        "_   _| |_| |_   _|                   |_|     |\n" +
                        " |_|         |_|                              \n" +
                        "                                              ";

        String map2 =
                        "                                          \n" +
                        "                              _           \n" +
                        "                            _| |_         \n" +
                        "                          _|     |_       \n" +
                        "      _   _       _______|         |_   _|\n" +
                        "_   _| |_| |_   _|                   |_|  \n" +
                        " |_|         |_|                          \n" +
                        "                                          ";

        List<Move> moves = new ArrayList<>();
        try {
            moves = compute(map2);
        } catch (IllegalArgumentException e) {
            System.out.println("Something unexpected happened! " + e.getMessage());
        }

        printRawInputOutput(map2, moves);

        printOutputStatistics(moves);

    }
}

