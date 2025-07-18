package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import com.mybatisplusdemo.model.domain.EIException;
import com.mybatisplusdemo.service.ConfigService;
import com.mybatisplusdemo.common.utils.Return;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked", "rawtypes"})
public class FileController {
    @Autowired
    private ConfigService configService;

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @IgnoreAuth
    public Return upload(@RequestParam("file") MultipartFile file, String type) throws Exception {
        if (file.isEmpty()) {
            throw new EIException("上传文件不能为空");
        }
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String pathStr = ResourceUtils.getURL("classpath:static").getPath();
        // 使用URLDecoder解码以支持中文路径
        pathStr = URLDecoder.decode(pathStr, "UTF-8");
        File path = new File(pathStr);
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "/file/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String fileName = new Date().getTime() + "." + fileExt;
        if (StringUtils.isNotBlank(type) && type.contains("_template")) {
            fileName = type + "." + fileExt;
            new File(upload.getAbsolutePath() + "/" + fileName).deleteOnExit();
        }
        File dest = new File(upload.getAbsolutePath() + "/" + fileName);
        file.transferTo(dest);
//		FileUtils.copyFile(dest, new File("D:\\cl123456\\src\\main\\resources\\static\\file"+"/"+fileName)); /**修改了路径以后请将该行最前面的//注释去掉**/
        if (StringUtils.isNotBlank(type) && type.equals("1")) {
            ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
            if (configEntity == null) {
                configEntity = new ConfigEntity();
                configEntity.setName("faceFile");
                configEntity.setValue(fileName);
            } else {
                configEntity.setValue(fileName);
            }
            configService.insertOrUpdate(configEntity);
        }
        return Return.ok().put("file", fileName);
    }

    /**
     * 下载文件
     */
    @IgnoreAuth
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName) {
        try {
            File path = new File(ResourceUtils.getURL("classpath:static").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            File upload = new File(path.getAbsolutePath(), "/file/");
            if (!upload.exists()) {
                upload.mkdirs();
            }
            File file = new File(upload.getAbsolutePath() + "/" + fileName);
            if (file.exists()) {
                /*if(!fileService.canRead(file, SessionManager.getSessionUser())){
                    getResponse().sendError(403);
                }*/
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", fileName);
                return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
