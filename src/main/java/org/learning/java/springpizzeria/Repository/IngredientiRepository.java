package org.learning.java.springpizzeria.Repository;

import org.learning.java.springpizzeria.Model.Ingredienti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Integer> {

}
