package ru.grigory.site.controller;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.grigory.site.domain.Partner;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.service.PartnerService;
import ru.grigory.site.service.ProductVersionService;
import ru.grigory.site.service.SettingsService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//    private final static String dir = "/usr/local/image";

    @Autowired
    private ProductVersionService productVersionService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private PartnerService partnerService;

    private String getStorageDir(){
        return settingsService.findByKey("storage_dir").getValue();
    }


    @ResponseBody
    @RequestMapping(value = "/thumbnail/{productId}/{versionId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgThumb(@PathVariable Long productId, @PathVariable Long versionId,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        File f = new File(getStorageDir(), productId + "_" + versionId +"_thumb.png");
        if(!f.exists()){
            return null;
        }else{
            return imgIfChanged(request, response, IOUtils.toByteArray(new FileInputStream(f)));
        }

    }

    private byte[] imgIfChanged(HttpServletRequest request, HttpServletResponse response, byte[] img) {
        String cashHeader = request.getHeader("If-None-Match");
        String md5 = DigestUtils.md5DigestAsHex(img);
        if(cashHeader != null){
            if(md5.equals(cashHeader)){
                response.setHeader("Cash-Control", "max-age=120");
                response.setHeader("ETag", md5);
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return null;
            }
        }
        response.setHeader("Cash-Control", "max-age=120");
        response.setHeader("ETag", md5);
        return img;
    }

    @ResponseBody
    @RequestMapping(value = "/version/{productId}/{versionId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgVersion(@PathVariable Long productId, @PathVariable Long versionId,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        File f = new File(getStorageDir(), productId + "_" + versionId + ".png");
        if(!f.exists()){
            return null;
        }else{
            return imgIfChanged(request, response, IOUtils.toByteArray(new FileInputStream(f)));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgProduct(@PathVariable Long productId,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {

        ProductVersion firstVersion = productVersionService.findFirstVersion(productId);
        if(firstVersion == null){
            return null;
        }
        File f = new File(getStorageDir(), productId + "_" + firstVersion.getId() + "_thumb.png");
        if(!f.exists()){
            return null;
        }else{
            return imgIfChanged(request, response, IOUtils.toByteArray(new FileInputStream(f)));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/partner/{partnerId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgPartner(@PathVariable Long partnerId,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {

        Partner partner = partnerService.findById(partnerId);
        if(partner == null){
            return null;
        }
        return imgIfChanged(request, response, partner.getBanner());
    }

}
