package hiyoweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {//class start

    // 어디에( PATH ) 누구를(파일객체) // 등록위치
    String uploadPath = "C:\\Users\\504\\Desktop\\HiYo_web2\\build\\resources\\main\\static\\uploadimg\\";


    public String  fileupload(MultipartFile multipartFile){
        // 난수 식별코드 생성
        String uuid = UUID.randomUUID().toString();

        // 업로드 할 경로 이름만들기
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");

        File file = new File(uploadPath + filename);

        try {
            // 파일 저장 (지정한 경로에 만든파일 복사 시키키)
            multipartFile.transferTo( file );
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }

        return filename;
    }

}//class end
