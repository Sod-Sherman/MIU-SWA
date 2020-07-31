package shop.shopping.service.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import shop.shopping.service.RestService;
import shop.shopping.service.dto.ProductDTO;

import java.io.IOException;
import java.net.URI;

@Service
public class ProductCatalogService {
    @Autowired
    private RestService restService;

    @Value("${webshop.service.product.url:http://localhost:8083}")
    private String productServiceUrl;

    public ProductDTO getProduct(String productNumber) throws IOException {
        URI url = URI.create(productServiceUrl + ProductConstant.PRODUCT + "/" + productNumber);
        return restService.getValueOfRestCall(url, HttpMethod.GET, "", ProductDTO.class);
    }
}
