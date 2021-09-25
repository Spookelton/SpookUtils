package net.spookelton.spookutils.restAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RamController {
    @GetMapping("/ram")
    public Ram ram() {
        return new Ram(
                (double) Runtime.getRuntime().totalMemory() / 1024 / 1024,
                (double) Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024
        );
    }

    private static class Ram {
        private final double total;
        private final double free;
        private final double used;

        public Ram(double totalRAM, double freeRAM, double usedRAM) {
            this.total = totalRAM;
            this.free = freeRAM;
            this.used = usedRAM;
        }

        public double getTotal() {
            return total;
        }

        public double getFree() {
            return free;
        }

        public double getUsed() {
            return used;
        }
    }
}
