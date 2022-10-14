package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.config.SecurityConfig;
import uz.pdp.internetmagazin.entity.Basket;
import uz.pdp.internetmagazin.entity.Product;
import uz.pdp.internetmagazin.exception.InputDataExistsException;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.BasketDTO;
import uz.pdp.internetmagazin.repository.BasketRepository;
import uz.pdp.internetmagazin.repository.ProductRepository;
import uz.pdp.internetmagazin.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final SecurityConfig securityConfig;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<?> add(BasketDTO basketDTO) {

//        User user = userRepository.findById(basketDTO.getUser_id()).orElseThrow(() -> new InputDataExistsException("User id not found"));

        if (basketDTO.getProduct_id() != null) {
            Basket basket = new Basket();
            Product product = productRepository.findById(basketDTO.getProduct_id()).orElseThrow(() -> new InputDataExistsException("Product id not found!!"));

            basket.setUser(securityConfig.getCurrentUser());
            basket.setProduct(product);

            basketRepository.save(basket);

            return ResponseEntity.ok(ApiResult.successResponce("Basket added"));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Malumot not found", HttpStatus.CONFLICT.value()));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from basket");
        if (maps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Malumot topilmadi!!", HttpStatus.CONFLICT.value()));
        }
        return ResponseEntity.ok(ApiResult.successResponce(maps));
    }

    @Override
    public ResponseEntity<?> get(Integer id) {
        String query = "select * from basket where id=" + id;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query);
        if (maps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Malumot topilmadi!!", HttpStatus.CONFLICT.value()));
        }
        return ResponseEntity.ok(ApiResult.successResponce(maps));
    }

    @Override
    public ResponseEntity<?> edit(Integer id, BasketDTO basketDTO) {
//        ?User user = userRepository.findById(basketDTO.getUser_id()).orElseThrow(() -> new InputDataExistsException("User id not found"));
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new InputDataExistsException("Basket id not found!!!"));
        Product product = productRepository.findById(basketDTO.getProduct_id()).orElseThrow(() -> new InputDataExistsException("Product id not found!!"));
        if (basketDTO.getProduct_id() != null) {
            basket.setUser(securityConfig.getCurrentUser());
            basket.setProduct(product);

            basketRepository.save(basket);

            return ResponseEntity.ok(ApiResult.successResponce("Basket edided"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Product null", HttpStatus.CONFLICT.value()));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new InputDataExistsException("Basket id not found"));

        basketRepository.delete(basket);

        return ResponseEntity.ok(ApiResult.successResponce("Basket delete ed !"));
    }
}
