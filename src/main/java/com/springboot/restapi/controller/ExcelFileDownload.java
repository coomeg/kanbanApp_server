package com.springboot.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelFileDownload {

    @Autowired
    protected ResourceLoader resourceLoader;

    @Value("classpath:templates/template.xlsx")
    Resource resourceFile;

    @PostMapping
	@RequestMapping(value = "/api/excelFileDownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Resource excelFileDownload() {
		System.out.println(resourceFile.getFilename());
		return resourceFile;
	}

}