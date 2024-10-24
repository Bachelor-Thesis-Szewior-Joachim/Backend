package org.example.backend.cryptocurrency.categories.repository;

import org.example.backend.cryptocurrency.categories.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByCategoryId(String categoryId);
}
