package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 *
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return 文件网络路径
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> uploadFile(MultipartFile file)
    {   log.info("文件上传:{}",file);
        try {
            //原始文件名
            String originFileName= file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension=originFileName.substring(originFileName.indexOf("."));
            //用UUID拼接拓展名
            String objectName= UUID.randomUUID()+extension;
            //文件的请求路径
           String filepath=   aliOssUtil.upload(file.getBytes(), objectName);
           return  Result.success(filepath);
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
