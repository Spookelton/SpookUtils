package net.spookelton.spookutils.restAPI;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class TPSController {
    private static final MinecraftServer server = FMLServerHandler.instance().getServer();

    @GetMapping("/tps")
    public TPS[] tps(@RequestParam(value = "dimension") String dimension) {
        if (dimension != null) {
            int dimIDNum = Integer.parseInt(dimension);
            return new TPS[]{ new TPS(dimIDNum, TPSFetch.GetTPS(dimIDNum)) };
        }
        return (TPS[])Arrays.stream(DimensionManager.getIDs()).map(
                (id) -> new TPS(id, GetTPS(id))
            ).toArray();
    }

    private static double GetTPS(int id) {
        long[] tickTimes = server.worldTickTimes.get(id);
        return Arrays.stream(tickTimes).sum() / (double)tickTimes.length;
    }
}
