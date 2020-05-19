package notes;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testRandomString() {
        Random random = new Random(5);
        assertThat(new Position(random)).hasToString("C | 5");
        assertThat(new Position(random)).hasToString("G | 3");
        assertThat(new Position(random)).hasToString("G\u266d | 6");
    }
}