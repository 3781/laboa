package team.oha.laboa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.oha.laboa.vo.ApiVo;

@Controller
@RequestMapping
public class PageController {

    @Value("WEB-INF/index.html")
    private Resource indexHtml;

    @GetMapping("/**")
    public Object pages(){
        return ResponseEntity.ok().body(indexHtml);
    }

    @ResponseBody
    @RequestMapping("/api/**")
    public ApiVo noFindApi(){
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("访问出错");
        return apiVo;
    }
}
