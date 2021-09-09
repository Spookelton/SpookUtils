package net.spookelton.spookutils.restAPI;

import net.minecraftforge.common.DimensionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DimensionStatsController {
    @GetMapping("/dimstats")
    public DimensionStats dimensionStats() {
        Integer[] loadedDimIDs = DimensionManager.getIDs();
        return new DimensionStats(loadedDimIDs);
    }
}
