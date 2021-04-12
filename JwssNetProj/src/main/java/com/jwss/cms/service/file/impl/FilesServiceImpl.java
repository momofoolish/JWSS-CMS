package com.jwss.cms.service.file.impl;

import com.jwss.cms.dao.file.TbFilesDao;
import com.jwss.cms.model.file.TbFiles;
import com.jwss.cms.service.file.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class FilesServiceImpl implements FilesService {
    private final Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Resource
    private TbFilesDao tbFilesDao;

    @Override
    public boolean fileUpload(MultipartFile mf, TbFiles tbFiles) {
        tbFiles.setState(0);
        tbFiles.setParentId(1);
        tbFilesDao.insert(tbFiles);
        return false;
    }
}
