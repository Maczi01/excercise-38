package pl.mati.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/offers")
public class OfferController {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/offers")
    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    @GetMapping("/api/offers")
    public List<Offer> search(@RequestParam(required = false) String title){
        if(title !=null){
            return offerRepository.findByTitle(title);
        }
        return offerRepository.findAll();
    }

    @GetMapping("/api/offers/count")
    public Long counter(){
        return offerRepository.count();
    }

    @GetMapping("/api/offers/{id}")
    public ResponseEntity<Offer> getAllOffers(@PathVariable Long id){
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if(optionalOffer.isPresent()){
            Offer offer = optionalOffer.get();
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/add")
    public ResponseEntity<Offer> add(@RequestBody Offer offer){
        if(offer.getTitle()!=null){
            return ResponseEntity.badRequest().build();
        }

        Offer savedoffer = offerRepository.save(offer);
        return ResponseEntity.ok(savedoffer);
    }
//    @PostMapping("/add")
//    public List<Category> add(@RequestBody Category category){
//        return categoryRepository.findAll();
//    }

    @GetMapping("/api/categories")
    public List<Category> getAllCat(){
        return categoryRepository.findAll();
    }




}
