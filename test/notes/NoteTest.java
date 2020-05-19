package notes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Test
    void testToString() {
        assertThat(Note.G).hasToString("G");
        assertThat(Note.A).hasToString("A");
        assertThat(Note.B_FLAT).hasToString("B\u266d");
        assertThat(Note.C_SHARP).hasToString("C\u266f");
    }
}