package pl.mati.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select name from category", nativeQuery = true)
    List<String> findAllNames();

//    List<String> findAllNames();
}

