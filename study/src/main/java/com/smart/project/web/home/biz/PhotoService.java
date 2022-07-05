package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public String savePhoto(MultipartFile file, String path){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c1 = Calendar.getInstance();


        String strToday = sdf.format(c1.getTime());


        String fileRealName = file.getOriginalFilename();
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:/mango/"+strToday+path+"/";
        String dbFolder = "/image/"+path+"/";
        log.error("파일 이름 : {}", fileRealName);
        log.error("파일 확장자 : {}", fileExtension);
        log.error("파일 폴더 : {}", uploadFolder);

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        String[] uuids = uuid.toString().split("-");

        String uniqueName = uuids[0];
        System.out.println("생성된 고유문자열 : " + uniqueName);
        System.out.println("확장자명 : " + fileExtension);

        //File folder = new File(uploadFolder+"\\"+form.getUserId());
        File saveFile = new File(uploadFolder+strToday+fileExtension);  // 적용 후

        try {
            file.transferTo(saveFile);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbFolder+strToday+fileExtension;
    }
}
