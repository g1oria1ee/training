package org.gloria.training.repo;

import org.gloria.training.model.Rectangles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RectangleRepo extends JpaRepository<Rectangles, Integer> {
}
