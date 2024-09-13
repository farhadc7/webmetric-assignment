package ir.webmetric.adrevenue.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileUtil<T> {

    ObjectMapper mapper= new ObjectMapper();
    public  <T> void convert(MultipartFile file,T t) throws IOException {
       Object res= mapper.readValue(file.getInputStream(),t.getClass());
       if(res != null){
           t= (T) res;
       }

    }

//    public  List<T> convertAll(MultipartFile file,TypeReference type) throws IOException {
//        List<T> res= mapper.readValue(file.getInputStream(), type);
//        if(res != null){
//            return res;
//        }
//        return null;
//
//    }

    public void validateFile(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new RuntimeException("input file is not valid.");
        }
    }

    public ResponseEntity<ByteArrayResource> createJsonResponse(String name, Object res) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonData = objectMapper.writeValueAsBytes(res);

        return ResponseEntity.ok().headers(createHeader(name,jsonData.length)).body(new ByteArrayResource(jsonData));
    }

    private HttpHeaders createHeader(String name, int length){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", name  + ".json");
        headers.setContentLength(length);
        return headers;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
