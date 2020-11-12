package generator;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class DiceRoller {

    public int dN(int max) {
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }

    public int d2() {return dN(2);}

    public int d4() {
        return ThreadLocalRandom.current().nextInt(1, 4 + 1);
    }

    public int d8() {
        return ThreadLocalRandom.current().nextInt(1, 8 + 1);
    }

    public int d6() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    public int roll3d6 () {
        return d6() + d6() + d6();
    }

}
