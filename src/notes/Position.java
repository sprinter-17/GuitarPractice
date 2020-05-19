package notes;

import java.util.Objects;
import java.util.Random;

public class Position {
    private final Note note;
    private final int string;

    public Position(Random random) {
        this.note = Note.values()[random.nextInt(Note.values().length)];
        this.string = random.nextInt(6) + 1;
    }

    public String toString() {
        return note + " " + string;
    }

    public boolean isSimilarTo(Position other) {
        return other != null && (note == other.note || string == other.string);
    }
}
