package com.redstream.webdav.service;

import com.redstream.webdav.helper.AuthHelper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class WebDavService {

    private String username = "uname";
    private String password = "passwd";

    public String query(String uri) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPropfind propfind = new HttpPropfind(uri, 1);
            propfind.setHeader("Authorization", AuthHelper.getBasicAuthHeader(username, password));

            try (CloseableHttpResponse response = client.execute(propfind)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }

    public String uploadFile(String uri, MultipartFile file) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            uri += "/" + file.getOriginalFilename();
            HttpPut put = new HttpPut(uri);
            put.setHeader("Authorization", AuthHelper.getBasicAuthHeader(username, password));

            put.setEntity(new FileEntity(convertToFile(file)));
            try (CloseableHttpResponse response = client.execute(put)) {
                return response.getStatusLine().toString();
            }
        }
    }

    public byte[] downloadFile(String uri) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(uri);
            get.setHeader("Authorization", AuthHelper.getBasicAuthHeader(username, password));
            try (CloseableHttpResponse response = client.execute(get)) {
                if (response.getEntity() != null) {
                    return EntityUtils.toByteArray(response.getEntity());
                } else {
                    return new byte[0]; // No content case
                }
            }
        }
    }


    public String deleteFile(String uri) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete delete = new HttpDelete(uri);
            delete.setHeader("Authorization", AuthHelper.getBasicAuthHeader(username, password));

            try (CloseableHttpResponse response = client.execute(delete)) {
                // You can handle different HTTP status codes here
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) { // HTTP 204 No Content means the deletion was successful
                    return "File deleted successfully";
                } else {
                    // If deletion is not successful, return the HTTP status line for debugging
                    return "Failed to delete file: " + response.getStatusLine().toString();
                }
            }
        }
    }

    /**
     * 将MultipartFile转换为File对象。
     *
     * @param multipartFile 需要转换的MultipartFile
     * @return 转换后的File对象
     * @throws IOException 如果文件写入失败
     */
    public File convertToFile(MultipartFile multipartFile) throws IOException {
        // 创建临时文件
        Path tempDir = Files.createTempDirectory("temp_files");
        // 为保证文件名的唯一性，可以使用原始文件名或生成一个随机文件名
        String fileName = multipartFile.getOriginalFilename(); // 获取原始文件名
        Path filePath = tempDir.resolve(fileName);
        File file = filePath.toFile();

        // 将MultipartFile的内容复制到新文件
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new IOException("Failed to convert MultipartFile to File.", e);
        }

        return file;
    }

}
