
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ScoreBoard {
    private int maxEntries;
    private int numEntries; // number of actual entries
    private GameEntry[] board; // array of game entries (name and scores)

    public ScoreBoard(int capacity) {
        maxEntries = capacity;
    }

   //Attempts to add a new score to the collection (if it is high enough).
    public void add(GameEntry e) {

        if(numEntries == 0) {
            board[numEntries] = e;
        }

        else if(numEntries>0 && numEntries < 10) {
            board[numEntries]=e;
            for(int i = 0;i<numEntries;i++) {
                if (e.score > board[i].score) {
                    for(int k = numEntries-1; k >= i; k--) {
                        board[k + 1] = board[k];
                    }
                    board[i] = e;
                    break;
                }
            }
        }

        else if (e.score > board[maxEntries - 1].score)  {
            int i;
            for (i = 0; i < maxEntries-1; i++) {
                if (board[i].score < e.score) {
                    board[maxEntries-1] = remove(maxEntries-1);
                    break;
                }
            }
            for(int k = maxEntries-2; k>=i;k--) {
                board[k+1] = board[k];
            }
            board[i]=e;
        }
        }

    //Attempts to remove an existing score from the collection
    public GameEntry remove(int j) throws IndexOutOfBoundsException {
        return null;
    }

    public String toString() {
        return Arrays.toString(board);
    }

    public static void main(String[] args) {

        ScoreBoard testBoard = new ScoreBoard(10);
        testBoard.board = new GameEntry[testBoard.maxEntries];
        testBoard.numEntries = 0;


        File file = new File("scores.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Scanner lineReader = new Scanner(line).useDelimiter(",\\s?+"); // comma followed by any number of spaces

                String name = lineReader.next();
                int score = lineReader.nextInt();

                GameEntry newEntry = new GameEntry(name, score);
                testBoard.add(newEntry);
                testBoard.numEntries++;

                lineReader.close();
            }
            scanner.close();

            testBoard.toString();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(testBoard.toString());

    }

}