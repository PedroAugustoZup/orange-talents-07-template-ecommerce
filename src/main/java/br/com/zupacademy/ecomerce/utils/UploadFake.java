package br.com.zupacademy.ecomerce.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadFake implements Uploader{
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(item ->
                "http://bucket.io/"+item.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
