package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.config.SecurityConfig;
import uz.pdp.internetmagazin.entity.Category;
import uz.pdp.internetmagazin.entity.Magazine;
import uz.pdp.internetmagazin.entity.User;
import uz.pdp.internetmagazin.exception.InputDataExistsException;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;
import uz.pdp.internetmagazin.repository.CategoryRepository;
import uz.pdp.internetmagazin.repository.MagazineRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepositary;
    private final SecurityConfig securityConfig;

    private final JdbcTemplate jdbcTemplate;

    private final MagazineRepository magazineRepository;


    @Override
    public ResponseEntity<?> add(CategoryDTO categoryDTO) {
        User currentUser = securityConfig.getCurrentUser();
        Integer id = currentUser.getId();
        if (id == 1) {
            Magazine magazine = magazineRepository.findById(categoryDTO.getMagazin_id()).orElseThrow(() -> new InputDataExistsException("Magazine not found"));
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setMagazin(magazine);
            category.setUser(securityConfig.getCurrentUser());
            categoryRepositary.save(category);
            return ResponseEntity.ok(ApiResult.successResponce("Category added"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResult.failResponse("Not Found forbiddin", HttpStatus.FORBIDDEN.value()));

    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from category where is_active = true");
        if (maps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Categoriala yoq", HttpStatus.CONFLICT.value()));
        }
        return ResponseEntity.ok(ApiResult.successResponce(maps));
    }


    @Override
    public ResponseEntity<?> edit(Integer id, CategoryDTO categoryDTO) {
        User currentUser = securityConfig.getCurrentUser();
        Integer id1 = currentUser.getId();
        if (id1 == 1) {
            Category category1 = categoryRepositary.findById(id).orElseThrow(() -> new InputDataExistsException("bunday category yoq"));
            Magazine magazin = magazineRepository.findById(categoryDTO.getMagazin_id()).orElseThrow(() -> new InputDataExistsException("bunday magazin yoq"));
            boolean b = categoryRepositary.existsByName(category1.getName());
            if (category1.isActive() && !b) {
                category1.setName(categoryDTO.getName());
                category1.setMagazin(magazin);
                categoryRepositary.save(category1);
                return ResponseEntity.ok(ApiResult.successResponce("success category"));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Bunday category mavjud", HttpStatus.CONFLICT.value()));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResult.failResponse("Not Found forbiddin", HttpStatus.FORBIDDEN.value()));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        User currentUser = securityConfig.getCurrentUser();
        Integer id1 = currentUser.getId();
        if (id1 == 1) {
            Category category = categoryRepositary.findById(id).orElseThrow(() -> new InputDataExistsException("bunday category yoq!"));
            if (category.isActive()) {
                category.setActive(false);
                categoryRepositary.save(category);
                return ResponseEntity.ok(ApiResult.successResponce("delete boldi!"));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Category not found", HttpStatus.CONFLICT.value()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResult.failResponse("Not Found forbiddin", HttpStatus.FORBIDDEN.value()));
    }
}
