package hello.servlet.web.frontcontroller.v4;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ControllerV4 {
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
