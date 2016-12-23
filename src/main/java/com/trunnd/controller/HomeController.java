package com.trunnd.controller;

import com.trunnd.dto.ProductDTO;
import com.trunnd.model.Product;
import com.trunnd.service.ProductService;
import com.trunnd.utils.FriendlyUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private Integer DEFAULT_PAGE_SIZE = 9;

    @Autowired
    private ProductService service;

    @RequestMapping(value = {"", "/trang-chu"})
    public String home(Model model) {
        model.addAttribute("products", convertToListDto(service.findTop8Product()));
        return "client/home";
    }

    @RequestMapping(value = "/san-pham")
    public String products(Model model, String type, Integer page) {
        String categories = null;
        String imageProductBanner =  null;
        String nameProductBanner = null;
        if (page == null) {
            page = 0;
        }

        if (type != null) {
            switch (type){
                case "op-tuong" :
                    categories = "GACH-OP-TUONG";
                    nameProductBanner = "Gạch men ốp tường";
                    imageProductBanner = "https://s3.amazonaws.com/dinhtrung-catgachgiare-images/op-tuong.jpg";
                    break;
                case "bong-kieng":
                    categories = "GACH-BONG-KIEN";
                    nameProductBanner = "Gạch men bóng kiếng";
                    imageProductBanner = "https://s3.amazonaws.com/dinhtrung-catgachgiare-images/bong-kieng.jpg";
                    break;
                case "ceramic" :
                    categories = "GACH-CARAMIC";
                    nameProductBanner = "Gạch Ceramic";
                    imageProductBanner = "https://s3.amazonaws.com/dinhtrung-catgachgiare-images/ceramic.jpg";
                    break;
                case "da-tu-nhien":
                    categories = "DA-TU-NHIEN";
                    nameProductBanner = "Đá tự nhiên";
                    imageProductBanner = "https://s3.amazonaws.com/dinhtrung-catgachgiare-images/da-tu-nhien.jpg";
                    break;
                default:
                    break;
            }
            model.addAttribute("products", convertToListDto(service.findByCategoriesPaging(page, DEFAULT_PAGE_SIZE, categories)));
        }else {
            imageProductBanner = "https://s3.amazonaws.com/dinhtrung-catgachgiare-images/default-banner-product.jpg";
            nameProductBanner = "Sản phẩm gia công năm  2017";
            model.addAttribute("products", convertToListDto(service.findPaging(page, DEFAULT_PAGE_SIZE)));
        }
        model.addAttribute("newProducts", convertToListDto(service.findNewestProduct()));
        model.addAttribute("bannerProduct", imageProductBanner);
        model.addAttribute("textBannerProduct", nameProductBanner);
        return "client/product";
    }

    @RequestMapping(value = "/san-pham/{link}.html")
    public String getProductById(@PathVariable("link") String link, Model model) {
        Integer id = FriendlyUrlUtil.getIdFromLink(link);
        model.addAttribute("product", convertToProductDTO(service.findByOne(id)));
        model.addAttribute("relatedProducts", convertToListDto(service.findRelatedProduct(id)));
        return "client/product-detail";
    }

    @RequestMapping(value = "/bang-bao-gia")
    public String getPrices() {
        return "client/prices";
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setImages(product.getImages());
        dto.setPrice(product.getPrice());
        dto.setCategories(product.getCategories());
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        return dto;
    }

    private Product convertToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setImages(dto.getImages());
        product.setPrice(dto.getPrice());
        product.setCategories(dto.getCategories());
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());

        return product;
    }

    private List<ProductDTO> convertToListDto(List<Product> products) {
        return products.stream().map(product -> convertToProductDTO(product)).collect(Collectors.toList());
    }

}
