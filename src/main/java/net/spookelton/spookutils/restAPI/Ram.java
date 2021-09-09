package net.spookelton.spookutils.restAPI;

public class Ram {
    private final double totalRAM;
    private final double freeRAM;
    private final double usedRAM;

    public Ram(double totalRAM, double freeRAM, double usedRAM) {
        this.totalRAM = totalRAM;
        this.freeRAM = freeRAM;
        this.usedRAM = usedRAM;
    }

    public double getTotalRAM() {
        return totalRAM;
    }

    public double getFreeRAM() {
        return freeRAM;
    }

    public double getUsedRAM() {
        return usedRAM;
    }
}
