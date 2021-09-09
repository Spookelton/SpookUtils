package net.spookelton.spookutils.restAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TPSController {

    @GetMapping("/tps")
    public TPS tps(@RequestParam(value = "dimID", defaultValue = "0") String dimID) {
        int dimIDNum = Integer.parseInt(dimID);
        return new TPS(dimIDNum, TPSFetch.GetTPS(dimIDNum));
    }
}
