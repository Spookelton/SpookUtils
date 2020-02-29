package mixu.spookutils.proxy;

import com.google.common.collect.ImmutableSet;
import mixu.spookutils.SpookUtils;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.Level;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ServerProxy extends CommonProxy {
    private static FileWriter file;

    @SuppressWarnings("unchecked")
    public static void dumpDimensions() {
        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject obj = new JSONObject();
        DimensionManager.getRegisteredDimensions().forEach((k,v)->obj.put(k.getId(),k.getName()));
        /*obj.put("0", "Overworld");
        obj.put("-1", "Nether");
        obj.put("1", "The End");*/
        try {
            File temporaryerTempfile = new File(FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+"/SpookUtils");
            if (!temporaryerTempfile.exists()) {temporaryerTempfile.mkdir();}
            // Constructs a FileWriter given a file name, using the platform's default charset
            File tempfile = new File(FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+"/SpookUtils/dimIDs.json");
            try {tempfile.delete();} catch(Exception e) {SpookUtils.logger.warn(e);}
            file = new FileWriter(FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+"/SpookUtils/dimIDs.json");
            file.write(obj.toJSONString());

        } catch (IOException e) {
            SpookUtils.logger.error(e);

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                SpookUtils.logger.error(e);
            }
        }
    }
}
