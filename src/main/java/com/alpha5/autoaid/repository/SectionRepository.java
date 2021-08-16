package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findBySectionName(String name);
    Section findBySectionId(long sectionId);
}
