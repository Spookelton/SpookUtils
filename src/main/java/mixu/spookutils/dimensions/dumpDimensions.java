package mixu.spookutils.dimensions;

import mixu.spookutils.SpookUtils;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class dumpDimensions {
    private static FileWriter file;

    @SuppressWarnings("unchecked") @SideOnly(Side.SERVER)
    public static boolean dumpDimensions() {
        JSONObject obj = new JSONObject();
        DimensionManager.getRegisteredDimensions().forEach((k, v)->obj.put(k.getId(),k.getName()));
        DimensionManager.getRegisteredDimensions().forEach((k,v)->k.getName());
        /*obj.put("0", "Overworld");
        obj.put("-1", "Nether");
        obj.put("1", "The End");*/
        try {
            File temporaryerTempfile = new File(SpookUtils.getSpookUtilsDirectory());
            if (!temporaryerTempfile.exists()) {temporaryerTempfile.mkdir();}
            // Constructs a FileWriter given a file name, using the platform's default charset
            File tempfile = new File(SpookUtils.getSpookUtilsDirectory() + "dimIDs.json");
            try {tempfile.delete();} catch(Exception e) {
                SpookUtils.logger.warn(e);
                return false;
            }
            file = new FileWriter(FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+"/SpookUtils/dimIDs.json");
            file.write(obj.toJSONString());

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
