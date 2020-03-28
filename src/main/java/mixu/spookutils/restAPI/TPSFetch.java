package mixu.spookutils.restAPI;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.text.DecimalFormat;

public class TPSFetch {
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");
    private static final MinecraftServer server = FMLServerHandler.instance().getServer();
    private static final double emptyTPS = 0.000;

    public static double GetTPS(int dimID) {
        Integer[] dimIDs = DimensionManager.getIDs();
        boolean exists = false;
        for (int i: dimIDs) {
            if (i == dimID) {
                exists = true;
                break;
            }
        }
        if (!exists) {return emptyTPS;}
        double worldTickTime = mean(server.worldTickTimes.get(dimID)) * 1.0E-6D;
        double worldTPS = Math.min(1000.0/worldTickTime, 20);
        return Double.parseDouble(TIME_FORMATTER.format(worldTickTime));
    }

    private static long mean(long[] values)
    {
        long sum = 0L;
        for (long v : values)
        {
            sum += v;
        }
        return sum / values.length;
    }
}
