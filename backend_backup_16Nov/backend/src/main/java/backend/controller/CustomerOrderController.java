
package backend.controller;

import backend.entity.CustomerOrder;
import backend.repository.CustomerOrderRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

    private final CustomerOrderRepository orderRepository;

    public CustomerOrderController(CustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        order.setOrderStatus("PLACED");
        return orderRepository.save(order);
    }

    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/status")
    public CustomerOrder updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}
