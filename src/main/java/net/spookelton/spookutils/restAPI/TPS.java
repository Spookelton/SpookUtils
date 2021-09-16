package net.spookelton.spookutils.restAPI;

public class TPS {

    private final long dimension;
    private final double tickTime;

    public TPS(int dimension, double tickTime) {
        this.dimension = dimension;
        this.tickTime = tickTime;
    }

    public long getDimension() {
        return dimension;
    }

    public double getTickTime() {
        return tickTime;
    }
}
