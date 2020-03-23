package mixu.spookutils.restAPI;

import com.google.common.collect.Maps;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import java.util.ArrayList;
import java.util.Map;

public class DimensionStats {
    private final Map<Integer, Map> dims;

    public DimensionStats(Integer[] dimIDs) {
        this.dims = Maps.newHashMap();
        for (int ID : dimIDs) {
            WorldServer curWorld = DimensionManager.getWorld(ID);
            Map<String, Object> curDimMap = Maps.newHashMap();
            ArrayList<Map> curDimPlayersList = new ArrayList<Map>();

            for (EntityPlayer player : curWorld.playerEntities) {
                Map<String, Object> curPlayerMap = Maps.newHashMap();
                Map<String, Integer> posMap = Maps.newHashMap();

                posMap.put("x", (int) player.posX);
                posMap.put("y", (int) player.posY);
                posMap.put("z", (int) player.posZ);

                curPlayerMap.put("name", player.getName());
                curPlayerMap.put("xpLevel", player.experienceLevel);
                curPlayerMap.put("uuid", player.getUniqueID());
                curPlayerMap.put("position", posMap);
                curPlayerMap.put("gamemode", player.isCreative() ? "creative" : player.isSpectator() ? "spectator" : "survival");
                curDimPlayersList.add(curPlayerMap);
            }

            curDimMap.put("name", DimensionManager.getProviderType(ID).getName());
            curDimMap.put("chunksLoaded", curWorld.getChunkProvider().getLoadedChunkCount());
            curDimMap.put("entities", curWorld.loadedEntityList.size());
            curDimMap.put("items", curWorld.countEntities(EntityItem.class));
            curDimMap.put("tileEntities", curWorld.loadedTileEntityList.size());
            curDimMap.put("players", curDimPlayersList);
            dims.put(ID, curDimMap);
        }
    }

    public Map<Integer, Map> getDims() {
        return dims;
    }
}
