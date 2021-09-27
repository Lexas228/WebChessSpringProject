package ru.vsu.chess.model.others;

public record Point(int x, int y) {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return false;

        if (!(other instanceof Point otherPoint))
            return false;

        return otherPoint.x == x && otherPoint.y == y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
