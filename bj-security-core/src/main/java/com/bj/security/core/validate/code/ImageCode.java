package com.bj.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by neko on 2018/3/9.
 */
public class ImageCode extends ValidateCode {

    //图片
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
