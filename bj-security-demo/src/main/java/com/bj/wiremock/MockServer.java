package com.bj.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 使用WireMock构建MockServer
 * Created by neko on 2018/3/6.
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        //指定端口和地址
        WireMock.configureFor(8062);
        //清空之前的配置
        WireMock.removeAllMappings();

        mock("/order/1","01");


    }

    /**
     * mock 的封装
     * @param url 地址
     * @param file mock txt文件名
     */
    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/"+file+".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8"),"\n");
        //构建一个mock
        WireMock.stubFor(WireMock.get(
                WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withBody(content)
                        .withStatus(200)));
    }
}
