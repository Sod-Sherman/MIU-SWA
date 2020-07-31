package shop.products.service;

import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.order.integration.model.Item;
import shop.order.integration.model.QueueItem;
import shop.products.domain.Product;
import shop.products.domain.Stock;
import shop.products.repository.ProductRepository;
import shop.products.service.dto.ProductAdapter;
import shop.products.service.dto.ProductDTO;

@Service
@Log4j2
public class ProductCatalogService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(String productnumber, String description, double price) {
        Product product = new Product(productnumber, description, price);
        persistToDB(product);

    }

    public ProductDTO getProduct(String productnumber) {
        Product product = retrieveFromDB(productnumber);
        return product != null ? ProductAdapter.getProductDTO(product) : null;
    }

    public void setStock(String productnumber, int quantity, String locationcode) {
        Optional<Product> result = productRepository.findById(productnumber);
        if (result.isPresent()) {
            Product product = result.get();
            Stock stock = new Stock(quantity, locationcode);
            product.setStock(stock);
            persistToDB(product);
        }
    }

    public int getStockQuantity(String productNumber) {
        Stock stock = getOrigProduct(productNumber).getStock();
        return stock != null ? stock.getQuantity() : -1;
    }

    private Product getOrigProduct(String prodNum) {
        return retrieveFromDB(prodNum);
    }

    public void updateStockQuantity(QueueItem queueItem) {
        if (queueItem == null || queueItem.getItems() == null) return;
        log.info("Updating QueueItemId: ", queueItem);
//        Product product;
        queueItem.getItems().forEach(i -> {
            Product product = getOrigProduct(i.getProductId());
            log.info(product);
            Stock stock = product.getStock();
            if (stock != null) {
                stock.setQuantity(stock.getQuantity() - i.getQuantity());
                product.setStock(stock);
                persistToDB(product);
            } else {
                log.error("Stock problem!!! QueueItemId: {}", queueItem.getQueueItemId());
            }

        });

    }

    private void persistToDB(Product product) {
        try {
            productRepository.save(product);
            log.info("Success persist to DB prodNum: {}", product.getProductnumber());
        } catch (Exception e) {
            log.error("DB error for prodId: {}, Reason: {} ", product.getProductnumber(), e);
        }
    }

    private Product retrieveFromDB(String productNum) {
        Product product = null;
        try {
            product = productRepository.findById(productNum).orElseThrow();
            log.info("Success retrieve from DB prodNum: {}", productNum);
        } catch (Exception e) {
            log.error("DB error: {} : {}", productNum, e);
        }

        return product;
    }
}
