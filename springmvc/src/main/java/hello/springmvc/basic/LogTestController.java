package hello.springmvc.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        logger.trace("t log = {}", name);
        logger.debug("do log = {}", name);
        logger.info("i log = {}", name);
        logger.warn("w log = {}", name);
        logger.error("e log = {}", name);

        return "ok";
    }
}
