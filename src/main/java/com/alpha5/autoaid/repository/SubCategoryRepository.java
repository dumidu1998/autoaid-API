package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Section;
import com.alpha5.autoaid.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findBySubCatNameAndSection(String subCatName, Section section);
    SubCategory findBySubCatName(String subName);
    SubCategory findBySection(Section section);
}
