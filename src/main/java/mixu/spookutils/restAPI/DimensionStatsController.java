package mixu.spookutils.restAPI;

import net.minecraft.command.EntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DimensionStatsController {
    @GetMapping("/dimstats")
    public DimensionStats dimensionStats() {
        Integer[] loadedDimIDs = DimensionManager.getIDs();
        return new DimensionStats(loadedDimIDs);
    }
}
