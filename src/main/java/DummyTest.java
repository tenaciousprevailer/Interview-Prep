import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DummyTest {
    enum Direction {
        DOWN, UP
    }

    private static class DirectedPosition {
        final int x;
        final int y;
        final Direction direction;
        DirectedPosition(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        List<Object[]> list = new ArrayList<>();
        list.sort(
                Comparator
                        .comparingInt((Object[] o) -> ((DirectedPosition) o[1]).x)
                        .thenComparingInt(o -> ((DirectedPosition) o[1]).y)
        );
        int a = 999999999;
    }
}
