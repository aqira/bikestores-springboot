package com.tutorial.bikestores.clients;

import com.tutorial.bikestores.clients.dto.*;
import com.tutorial.bikestores.production.product.Product;
import com.tutorial.bikestores.production.product.ProductRepository;
import com.tutorial.bikestores.production.stock.Stock;
import com.tutorial.bikestores.production.stock.StockId;
import com.tutorial.bikestores.production.stock.StockRepository;
import com.tutorial.bikestores.sales.customer.Customer;
import com.tutorial.bikestores.sales.customer.CustomerRepository;
import com.tutorial.bikestores.sales.order.Order;
import com.tutorial.bikestores.sales.order.OrderRepository;
import com.tutorial.bikestores.sales.orderitem.OrderItem;
import com.tutorial.bikestores.sales.orderitem.OrderItemId;
import com.tutorial.bikestores.sales.staff.Staff;
import com.tutorial.bikestores.sales.store.Store;
import com.tutorial.bikestores.sales.store.StoreRepository;
import com.tutorial.bikestores.shared.DropdownDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;

    private final int PAGE_SIZE = 20;

    @Override
    public Page<ProductCatalogueDTO> getProductCatalogues(ProductCatalogueSearchDTO searchDTO) {
        PageRequest pageRequest = PageRequest.of(searchDTO.getPageNumber() - 1, PAGE_SIZE);
        return productRepository.findProductCatalogues(
                searchDTO.getName(),
                searchDTO.getBrandId(),
                searchDTO.getCategoryId(),
                searchDTO.getModelYear(),
                searchDTO.getListPrice(),
                pageRequest);
    }

    @Override
    public Page<ProductCatalogueDTO> getProductCatalogues(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
        return productRepository.findProductCatalogues(pageRequest);
    }

    @Override
    public ProductCatalogueDTO getProductCatalogueById(int id) {
        return productRepository.findProductCatalogueById(id);
    }

    @Override
    public void addToCart(AddToCartDTO addToCartDTO) {
        //TODO : tambahin ke keranjang berdasarkan logged-in customer, dan dicocokan dengan tabel stock
        Customer customer = customerRepository.findByUsername(addToCartDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("No Customer found with that username"));
        customer.getCart().add(new Stock(new StockId(addToCartDTO.getProductId(), addToCartDTO.getStoreId())));

        customerRepository.save(customer);
    }

    @Override
    public List<DropdownDTO> findAllStoreDropdowns(Integer productId) {
        return storeRepository.findAllStoreDropdowns(productId);
    }

    @Override
    public List<CartDTO> getCustomerCartsByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No Customer found with that username"));

        Map<String, CartDTO> cartByStore = new HashMap<>();
        for (Stock stock : customer.getCart()) {
            if (!cartByStore.containsKey(stock.getStore().getName()))
                cartByStore.put(stock.getStore().getName(),
                        new CartDTO(stock.getStore().getId(), stock.getStore().getName()));

            Product product = stock.getProduct();

            CartItemDTO cartItem = new CartItemDTO(product.getId(), product.getName(), product.getListPrice());
            cartByStore.get(stock.getStore().getName())
                    .getCartItems()
                    .add(cartItem);
        }

        return new ArrayList<>(cartByStore.values());
    }

    public CartDTO getCustomerCartByStoreId(String username, Integer storeId) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No customer found with that username"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("No store with that ID"));

        List<CartItemDTO> cartItems = customer.getCart()
                .stream()
                .filter(stock -> stock.getStore().getId().compareTo(store.getId()) == 0)
                .map(stock -> new CartItemDTO(
                        stock.getProduct().getId(),
                        stock.getProduct().getName(),
                        stock.getProduct().getListPrice()))
                .toList();


        return new CartDTO(store.getId(), store.getName(), cartItems);
    }

    @Override
    @Transactional
    public void orderProducts(OrderCartDTO orderCartDTO) {

        Order order = createOrder(orderCartDTO.getUsername(), orderCartDTO.getStoreId(), 1);
        orderRepository.save(order);

        populateOrderItems(order, orderCartDTO.getProductIds());

        updateStockAndRemoveFromCart(order.getCustomer(), orderCartDTO.getStoreId(), orderCartDTO.getProductIds());

    }

    private Order createOrder(String customerUsername, Integer storeId, Integer staffId) {
        Order order = new Order();
        Customer customer = customerRepository.findByUsername(customerUsername)
                .orElseThrow(() -> new EntityNotFoundException("No customer found with that username"));

        order.setCustomer(customer);
        order.setOrderStatus(1);
        order.setOrderDate(LocalDate.now());
        order.setRequiredDate(LocalDate.now().plusDays(7));

        order.setStore(new Store(storeId));
        order.setStaff(new Staff(staffId));
        return order;
    }

    private void updateStockAndRemoveFromCart(Customer customer, Integer storeId, List<Integer> productIds) {
        for (Integer productId : productIds) {
            StockId stockId = new StockId(productId, storeId);

            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() -> new EntityNotFoundException("No Stock for these product and store"));
            decreaseStockQuantity(stock);

            customer.getCart().remove(stock);
        }
    }

    private void decreaseStockQuantity(Stock stock) {
        if (stock.getQuantity() > 0) {
            stock.setQuantity(stock.getQuantity() - 1);
        } else {
            throw new IllegalStateException(String.format("""
                            Insufficient stock for product: %s in %s""",
                    stock.getId().getProductId(), stock.getId().getStoreId()));
        }
    }

    private void populateOrderItems(Order order, List<Integer> productIds) {
        int itemIdCounter = 0;

        for (Integer productId : productIds) {

            OrderItem orderItem = new OrderItem();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product with this ID does not exist"));
            orderItem.setProduct(product);

            OrderItemId orderItemId = new OrderItemId(order.getId(), ++itemIdCounter);
            orderItemId.setOrderId(order.getId());
            orderItem.setId(orderItemId);
            orderItem.setQuantity(1);
            orderItem.setListPrice(product.getListPrice());
            orderItem.setDiscount(0.0);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }
    }

}
