package com.jwss.cms.service.file;

import com.jwss.cms.model.file.TbFiles;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
    boolean fileUpload(MultipartFile mf, TbFiles tbFiles);
}
