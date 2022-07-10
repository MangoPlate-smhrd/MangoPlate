package com.smart.project.web.home.biz;

import com.smart.project.common.vo.InternCookie;
import com.smart.project.proc.Test;
import com.smart.project.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PhotoService {

    private static final String ROOTPATH = "C:/mango";

    public String savePhoto(MultipartFile file, InternCookie internCookie){


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());
        String fileRealName = file.getOriginalFilename();
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String path = File.separator + strToday.substring(0,4) + File.separator + strToday.substring(4, 6) + File.separator + strToday.substring(6, 8);
        String uploadFolder = "C:/mango"+path+File.separator;
        String folderPath = "C:/mango"+path;
        log.error("파일 이름 : {}", fileRealName);
        log.error("파일 확장자 : {}", fileExtension);
        log.error("파일 폴더 : {}", uploadFolder);

        String uuid = UUID.randomUUID().toString();

        String uploadFileName = strToday + "_" + internCookie.getName() + "_" + uuid.split("-")[0];

        System.out.println("파일 이름 : " + uploadFileName);
        System.out.println("확장자명 : " + fileExtension);

        File folder = new File(folderPath);
        if(!folder.exists()){
            try{
                folder.mkdirs();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        File saveFile = new File(uploadFolder+uploadFileName+fileExtension);  // 적용 후
        try {
            file.transferTo(saveFile);


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadFileName+fileExtension;
    }

    public String updatePhoto(MultipartFile file, InternCookie internCookie, String deleteFileName){
        String substring = deleteFileName.substring(0, 8);
        String deletePath = File.separator + substring.substring(0,4) + File.separator + substring.substring(4, 6) + File.separator + substring.substring(6, 8);

        File deleteFile = new File(ROOTPATH + deletePath + File.separator + deleteFileName);
        log.error("deleteFile = {}", deleteFile);
        deleteFile.delete();
        String path = savePhoto(file, internCookie);

        return path;
    }
}
