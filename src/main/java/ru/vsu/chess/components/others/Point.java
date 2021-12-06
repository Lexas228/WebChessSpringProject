package ru.vsu.chess.components.others;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point{
    private int x;
    private int y;

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
        return  x+""+y;
    }
}
