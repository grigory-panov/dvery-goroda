package ru.grigory.site.controller;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.service.ProductVersionService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 01.10.14
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ImagesController {

    private final static String dir = "/usr/local/image";

    @Autowired
    private ProductVersionService productVersionService;


    @ResponseBody
    @RequestMapping(value = "/thumbnail/{productId}/{versionId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgThumb(@PathVariable Long productId, @PathVariable Long versionId) throws IOException {
        File f = new File(dir, productId + "_" + versionId +"_thumb.png");
        if(!f.exists()){
            return null;
        }else{
            return IOUtils.toByteArray(new FileInputStream(f));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/version/{productId}/{versionId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgVersion(@PathVariable Long productId, @PathVariable Long versionId) throws IOException {
        File f = new File(dir, productId + "_" + versionId + ".png");
        if(!f.exists()){
            return null;
        }else{
            return IOUtils.toByteArray(new FileInputStream(f));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgProduct(@PathVariable Long productId) throws IOException {

        ProductVersion firstVersion = productVersionService.findFirstVersion(productId);
        if(firstVersion == null){
            return null;
        }
        File f = new File(dir, productId + "_" + firstVersion.getId() + "_thumb.png");
        if(!f.exists()){
            return null;
        }else{
            return IOUtils.toByteArray(new FileInputStream(f));
        }

    }


    public static void storeFile(MultipartFile file, Long productVersionId, Long productId) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        ImageIO.write(Scalr.resize(image, 800), "png", new File(dir, productId + "_" + productVersionId + ".png"));
        ImageIO.write(Scalr.resize(image, 200), "png",  new File(dir, productId + "_" + productVersionId + "_thumb.png"));
    }
}
