package br.com.zupacademy.ecomerce.dto.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImagensRequest {

    @NotNull
    @Size(min = 1)
    List<MultipartFile> imagens = new ArrayList<>();

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return this.imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagensRequest)) return false;
        ImagensRequest that = (ImagensRequest) o;
        return Objects.equals(getImagens(), that.getImagens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImagens());
    }
}
