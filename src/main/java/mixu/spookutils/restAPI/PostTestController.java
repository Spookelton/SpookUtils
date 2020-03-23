package mixu.spookutils.restAPI;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mixu.spookutils.SpookUtils;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PostTestController {
    @PostMapping(value = "/posttest", consumes = "application/json", produces = "application/json")
    public ResponseEntity serverSay(HttpServletRequest request) {
        String whatToSay = request.getHeader("toSay");
        if (whatToSay == null) {
            SpookUtils.logger.info("whatToSay is null");
            String error = "toSay not defined in request headers";
            Map<String, String> unFunniMap = Maps.newHashMap();
            unFunniMap.put("response", "toSay header is empty");
            return ResponseEntity.badRequest().body(new GsonBuilder().setPrettyPrinting().create().toJson(unFunniMap));
        }
        FMLServerHandler.instance().getServer().getCommandManager().executeCommand(FMLServerHandler.instance().getServer(), "say " + whatToSay);
        SpookUtils.logger.info(whatToSay);
        Map<String, String> funniMap = Maps.newHashMap();
        funniMap.put("response:", String.format("Sent %s as server", whatToSay));
        return ResponseEntity.ok().body(new GsonBuilder().setPrettyPrinting().create().toJson(funniMap));
    }
}
