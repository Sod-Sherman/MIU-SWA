package shop.shopping.service.orderservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import shop.shopping.service.RestService;
import shop.shopping.service.dto.ShoppingCartDTO;

import java.io.IOException;
import java.net.URI;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private RestService restService;

    @Value("${webshop.service.order.url}")
    private String orderServiceUrl;

    //    TODO url=inject
    public String createOrder(ShoppingCartDTO shoppingCartDTO) throws IOException {

        String orderId = restService.getValueOfRestCall(URI.create(orderServiceUrl + "/order/create"),
                HttpMethod.POST, shoppingCartDTO, String.class);
        log.info("Order Id: {} for shopping cart: {}", orderId, shoppingCartDTO.getCartid());
        return orderId;
    }
}
