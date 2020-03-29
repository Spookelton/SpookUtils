<<<<<<< Updated upstream
package mixu.spookutils.restAPI;

import net.minecraftforge.common.DimensionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< Updated upstream
@RestController
public class DimensionStatsController {
    @GetMapping("/dimstats")
    public DimensionStats dimensionStats() {
        Integer[] loadedDimIDs = DimensionManager.getIDs();
        return new DimensionStats(loadedDimIDs);
    }
}
=======
package mixu.spookutils.restAPI;

import net.minecraftforge.common.DimensionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

=======
>>>>>>> Stashed changes
@RestController
public class DimensionStatsController {
    @GetMapping("/dimstats")
    public DimensionStats dimensionStats() {
        Integer[] loadedDimIDs = DimensionManager.getIDs();
        return new DimensionStats(loadedDimIDs);
    }
}
>>>>>>> Stashed changes
