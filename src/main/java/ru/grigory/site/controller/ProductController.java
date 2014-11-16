package ru.grigory.site.controller;

import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.domain.Category;
import ru.grigory.site.domain.Product;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.dto.ProductDto;
import ru.grigory.site.dto.ProductListDto;
import ru.grigory.site.dto.ProductVersionDto;
import ru.grigory.site.exception.BusinessException;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.ProductService;
import ru.grigory.site.service.ProductVersionService;
import ru.grigory.site.service.SettingsService;
import ru.grigory.site.web.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 29.07.14
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ProductController {
    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVersionService productVersionService;

    @Autowired
    private SettingsService settingsService;

    private String getStorageDir(){
        return settingsService.findByKey("storage_dir").getValue();
    }


    @RequestMapping(value = "product.html", method = RequestMethod.GET)
    public ModelAndView getProduct() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title", "Описание товара");

        return new ModelAndView("product", params);
    }

    @RequestMapping(value = "admin/productList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getProductList(@RequestParam(value = "categoryId") long categoryId,
                                       @RequestParam(value = "message", required = false) String message) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category", categoryService.findById(categoryId));
        params.put("products", productService.findByCategory(categoryId));
        params.put("global", settingsService.findAllAsMap());
        String resolvedMesage = Utils.resolveMessage(message);
        if(resolvedMesage != null){
            params.put("message", resolvedMesage);
            params.put("messageClass",message.startsWith("error.") ? "text-danger" : "text-info");
        }

        params.put("title", "Редактирование товаров в категории");

        return new ModelAndView("admin/productList", params);
    }

    @RequestMapping(value = "admin/productVersionList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getProductVersionList(@RequestParam(value = "productId", required = true) long productId,
                                              @RequestParam(value = "message", required = false) String message) {
        Map<String, Object> params = new HashMap<String, Object>();
        Product product = productService.findById(productId);

        params.put("category", categoryService.findById(product.getCategoryId()));
        params.put("global", settingsService.findAllAsMap());
        params.put("product", product);
        String resolvedMesage = Utils.resolveMessage(message);
        if(resolvedMesage != null){
            params.put("message", resolvedMesage);
            params.put("messageClass", message.startsWith("error.") ? "text-danger" : "text-info");
        }
        params.put("productVersions", productVersionService.findByProduct(productId));
        params.put("title", "Редактирование версий товара");

        return new ModelAndView("admin/productVersionList", params);
    }

    @RequestMapping(value = "admin/productVersionEdit.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView editProductVersion(@RequestParam(value = "id") long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        ProductVersion productVersion = productVersionService.findById(id);
        if (productVersion == null) {
            throw new BusinessException("Product version not found!");
        }
        Product product = productService.findById(productVersion.getProductId());
        params.put("productVersion", productVersionService.findById(id));
        params.put("global", settingsService.findAllAsMap());
        params.put("product", product);
        params.put("title", "Редактирование версии");

        return new ModelAndView("admin/productVersionEdit", params);
    }

    @RequestMapping(value = "admin/productVersionAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addProductVersion(@RequestParam(value = "productId") long productId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product", productService.findById(productId));
        params.put("global", settingsService.findAllAsMap());
        params.put("order", productVersionService.getMaxOrderInProduct(productId) + 1 );
        params.put("title", "Добавление версии");

        return new ModelAndView("admin/productVersionAdd", params);
    }

    @RequestMapping(value = "admin/productVersionList.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public String saveProductVersion(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "productId", required = true) Long productId,
                                     @RequestParam(value = "price", required = true) BigDecimal price,
                                     @RequestParam(value = "order", required = true) Integer order,
                                     @RequestParam(value = "size", required = true) String size,
                                     @RequestParam(value = "img", required = false) MultipartFile file) {

        ProductVersion productVersion;
        String code;
        if (id != null) {
            productVersion = productVersionService.findById(id);
        } else {
            productVersion = new ProductVersion();
        }
        if (productVersion != null) {
            productVersion.setName(name);
            productVersion.setDescription(description);
            productVersion.setProductId(productId);
            productVersion.setPrice(price);
            productVersion.setOrder(order);
            productVersion.setSize(size);

            try {
                if (productVersion.getId() == null) {
                    productVersionService.addProductVersion(productVersion);
                } else {
                    productVersionService.updateProductVersion(productVersion);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                code = "error.database";
            }
            code = "message.OK";
            if (!file.isEmpty()) {
                try {
                    storeFile(file, productVersion.getId(), productId);
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                    code = "error.productVersion.image";
                }
            }

        } else {
            code = "error.productVersion.notfound";
        }

        return "redirect:/admin/productVersionList.html?productId="+productId +"&message="+ code;
    }

    @RequestMapping(value = "admin/productEdit.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView editProduct(@RequestParam(value = "id") long productId) {
        Map<String, Object> params = new HashMap<String, Object>();
        Product product = productService.findById(productId);
        if (product == null) {
            params.put("category", categoryService.findAll().get(0));
        } else {
            params.put("category", categoryService.findById(product.getCategoryId()));
        }
        params.put("productRelations", productService.getRelatedProducts(productId));
        params.put("productVersions", productVersionService.findByProduct(productId));
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("product", product);
        params.put("title", "Редактирование товара");

        return new ModelAndView("admin/productEdit", params);
    }

    @RequestMapping(value = "admin/productAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addProduct(@RequestParam(value = "categoryId") long categoryId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("category", categoryService.findById(categoryId));
        params.put("global", settingsService.findAllAsMap());
        params.put("order", productService.getMaxOrderByCategory(categoryId) + 1);
        params.put("title", "Добавление товара");

        return new ModelAndView("admin/productAdd", params);
    }

    @RequestMapping(value = "admin/productList.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public String saveProduct(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "categoryId", required = true) Long categoryId,
                              @RequestParam(value = "newCategoryId", required = true) Long newCategoryId,
                              @RequestParam(value = "order", required = true) int order) {
        String code;
        Product product;
        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        if (product != null) {
            product.setName(name);
            product.setDescription(description);
            product.setCategoryId(newCategoryId);
            product.setOrder(order);
            try {
                if (product.getId() == null) {
                    productService.addProduct(product);
                } else {
                    productService.updateProduct(product);
                }
                code = "message.OK";
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                code = "error.database";
            }
        } else {
            code = "error.product.notfound";
        }
        return "redirect:/admin/productList.html?categoryId=" + categoryId +"&message="+ code;
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductDto getProductVersionsJSON(@RequestParam(value = "id") long id) {

        Product product = productService.findById(id);


        //todo: ошибки должны быть оработаны и возвращены в теге errorText
        ProductDto result = new ProductDto();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setDescription(product.getDescription());
        List<ProductVersion> versions = productVersionService.findByProduct(id);
        ProductVersionDto[] versionsDto = new ProductVersionDto[versions.size()];
        int i = 0;
        for (ProductVersion v : versions) {
            ProductVersionDto dto = new ProductVersionDto();
            dto.setDescription(v.getDescription());
            dto.setId(v.getId());
            dto.setPrice(v.getPrice());
            dto.setSize(v.getSize());
            versionsDto[i] = dto;
            i++;
        }
        result.setVersions(versionsDto);
        return result;
    }

    @RequestMapping(value = "/related", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductListDto getProductRelatedJSON(@RequestParam(value = "id") long id) {

        ProductListDto result = new ProductListDto();
        Product product = productService.findById(id);
        if(product == null){
            return result;
        }
        List<Product> relatedProducts = productService.getRelatedProducts(id);
        result.setProduct(new ProductDto[relatedProducts.size()]);
        int i = 0;
        for(Product p : relatedProducts){
            ProductDto dto = new ProductDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            result.getProduct()[i++] = dto;
        }
        return result;
    }

    @RequestMapping(value = "admin/productRelationAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addProductRelation(@RequestParam(value = "productId") long productId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("product", productService.findById(productId));
        params.put("global", settingsService.findAllAsMap());
        params.put("title", "Добавление товара");

        return new ModelAndView("admin/productRelationAdd", params);
    }

    @RequestMapping(value = "admin/productEdit.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public String saveProductRelation(@RequestParam(value = "productId") long productId,
                                            @RequestParam(value = "product") long relationId) {
        String code = "OK";
        try {
            productService.addRelation(productId, relationId);
        }catch (Exception ex){
            code = "error";
        }
        return "redirect:/admin/productEdit.html?id=" +productId +"&message="+ code;
    }

    private void storeFile(MultipartFile file, Long productVersionId, Long productId) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        ImageIO.write(Scalr.resize(image, 800), "png", new File(getStorageDir(), productId + "_" + productVersionId + ".png"));
        ImageIO.write(Scalr.resize(image, 200), "png",  new File(getStorageDir(), productId + "_" + productVersionId + "_thumb.png"));
    }
}
