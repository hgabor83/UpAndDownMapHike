import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestUpAndDown {

    @Test
    public void testCompute() {
        String map1 =
                "_|\n" +
                        "  ";
        Assertions.assertEquals(List.of(Move.UP), UpAndDown.compute(map1));

        String map2 =
                "_ \n" +
                        " |";
        Assertions.assertEquals(List.of(Move.DOWN), UpAndDown.compute(map2));

        String map3 =
                "__\n" +
                        "  ";
        Assertions.assertEquals(List.of(Move.STAY), UpAndDown.compute(map3));

    }

}

