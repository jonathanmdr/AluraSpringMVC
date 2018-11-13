package br.com.casadocodigo.loja.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class FileSaver {

    @Autowired
    private HttpServletRequest request;

    public String write(String baseFolder, MultipartFile file) {
        try {
            String realPath = request.getServletContext().getRealPath("/".concat(baseFolder));
            String path = realPath.concat("/").concat(file.getOriginalFilename());

            file.transferTo(new File(path));

            return baseFolder.concat("/").concat(file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
