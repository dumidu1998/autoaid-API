package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Section;
import com.alpha5.autoaid.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findBySubCatNameAndSection(String subCatName, Section section);
    SubCategory findBySubCatId(long subCatId);
    SubCategory findBySubCatName(String subName);
    List<SubCategory> findAllBySection(Section section);
}
