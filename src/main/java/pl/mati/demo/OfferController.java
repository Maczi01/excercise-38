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
//
//    @GetMapping("/api/offers")
//    public List<Offer> getAllOffers(){
//        return offerRepository.findAll();
//    }
//TODO
    @GetMapping("/api/offers")
    public List<Offer> search(@RequestParam(required = false) String title){
//        String nothing = title;
        if(title ==null || title == "" || title ==" "){
            return offerRepository.findAll();
        }
        else {
            return offerRepository.findByTitle(title);
        }
    }


//    @GetMapping("/api/offers")
//    public List<Offer> search(@RequestParam(required = false) String title) {
//        List<Offer> temporaryList;
//        if (title == "") {
//            temporaryList = offerRepository.findAll();
//        } else {
//            temporaryList = offerRepository.findByTitle(title);
//        }
//        return temporaryList;
//    }


    @GetMapping("/api/offers/count")
    public Long counter() {
        return offerRepository.count();
    }

    @GetMapping("/api/offers/{id}")
    public ResponseEntity<Offer> getOfferBy(@PathVariable Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/api/offers")
//    @ResponseBody
//    public ResponseEntity<Offer> add(@RequestBody Offer offer){
//        if(offer.getTitle()!=null){
//            return ResponseEntity.badRequest().build();
//        }
//        Offer savedoffer = offerRepository.save(offer);
//        return ResponseEntity.ok(savedoffer);
//    }


    @PostMapping("/api/offers")
    @ResponseBody
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        if (offer.getId() != null) {
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
            bodyBuilder.build();
        }
        Offer save = offerRepository.save(offer);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/api/categories")
    public List<Category> getAllCat() {
        return categoryRepository.findAll();
    }

    @DeleteMapping("api/offers/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerRepository.deleteById(id);
    }


}
