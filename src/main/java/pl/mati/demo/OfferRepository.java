package pl.mati.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

        List<Offer> findByTitle(String title);

}
