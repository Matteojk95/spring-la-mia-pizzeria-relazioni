package org.learning.java.springpizzeria.Repository;

import org.learning.java.springpizzeria.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
