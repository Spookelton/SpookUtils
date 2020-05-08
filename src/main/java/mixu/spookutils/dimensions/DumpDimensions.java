package mixu.spookutils.dimensions;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mixu.spookutils.SpookUtils;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DumpDimensions {
    private static FileWriter file;

    @SuppressWarnings("unchecked") @SideOnly(Side.SERVER)
    public static boolean dumpDimensions() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<Integer, String> dimensions = Maps.newHashMap();
        DimensionManager.getRegisteredDimensions().forEach((k, v)->dimensions.put(k.getId(), k.getName()));
        
        try {
            File temporaryerTempfile = new File(SpookUtils.getSpookUtilsDirectory());
            if (!temporaryerTempfile.exists()) {temporaryerTempfile.mkdir();}
            File tempfile = new File(SpookUtils.getSpookUtilsDirectory() + "dimIDs.json");
            try {tempfile.delete();} catch(Exception e) {
                SpookUtils.logger.warn(e);
                return false;
            }
            file = new FileWriter(FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+"/SpookUtils/dimIDs.json");
            file.write(gson.toJson(dimensions, Map.class));

        } catch (IOException e) {
            SpookUtils.logger.error(e);
            return false;

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                SpookUtils.logger.error(e);
                return false;
            }
        }
        return true;
    }
}
