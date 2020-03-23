package mixu.spookutils.restAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RamController {
    @GetMapping("/ram")
    public Ram ram() {
        return new Ram((double) Runtime.getRuntime().totalMemory()/1024/1024, (double) Runtime.getRuntime().freeMemory()/1024/1024, (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024/1024);
    }
}
