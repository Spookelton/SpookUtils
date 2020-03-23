package mixu.spookutils.restAPI;

public class TPS {

    private final long dimID;
    private final double tickTime;

    public TPS(int dimID, double tickTime) {
        this.dimID = dimID;
        this.tickTime = tickTime;
    }

    public long getDimID() {
        return dimID;
    }

    public double getTickTime() {
        return tickTime;
    }
}
