package pl.mati.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //        @PostMapping("/api/add")
//    public List<Category> add(@RequestBody Category category){
//        return categoryRepository.findAll();
//    }

    @GetMapping("/api/categories/names")
    public List<String> getAllCategory(){
        return categoryRepository.findAllNames();
    }

    @PostMapping("/api/categories")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        if(category.getName()!=null){
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
            bodyBuilder.build();
        }
        Category save = categoryRepository.save(category);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("api/categories/{id}")
    public void deleteOffer(@PathVariable Long id){
        categoryRepository.deleteById(id);
    }

}
