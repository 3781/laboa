package team.oha.laboa.controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.util.FileUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiDto upload(@RequestParam(name = "file") CommonsMultipartFile file, HttpServletRequest request) {
        String rootPath = request.getServletContext().getRealPath("").replace( "\\", "/" );
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(FileUtil.upload(rootPath,"files", file));
        return apiDto;
    }
}
