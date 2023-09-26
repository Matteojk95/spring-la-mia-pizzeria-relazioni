package org.learning.java.springpizzeria.Repository;

import org.learning.java.springpizzeria.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    
}
