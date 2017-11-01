public class GameEntry {
    /** name of the person earning this score */
    protected String name;

    /** the score value */
    protected int score;

    /** Constructor to create a game entry */
    public GameEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /** Retrieves the name field */
    public String getName() {
        return this.name;
    }

    /** Retrieves the score field */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the name field
     *
     * @param {String}
     *            name - Given a person's name
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * Sets the score field
     *
     * @param {int}
     *            score - Given the score the person got
     */
    public void setScore(int score) {
        this.score = score;
    }

    /** Returns a string representation of this entry */
    public String toString() {
        return name+","+score;
    }
}