import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestUpAndDown {

    @Test
    void computeWithGoodMaps() {
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

    @Test
    void computeWithWrongMap() {
        String wrongMap1 = "A" + "  ";
        Assertions.assertThrows(IllegalArgumentException.class,() -> UpAndDown.compute(wrongMap1));

        String wrongMap2 = "_" + "  ";
        Assertions.assertThrows(IllegalArgumentException.class,() -> UpAndDown.compute(wrongMap2));
    }

    @Test
    void computeWithEmptyMap() {
        String emptyMap = "";
        Assertions.assertThrows(IllegalArgumentException.class,() -> UpAndDown.compute(emptyMap));
    }

}

