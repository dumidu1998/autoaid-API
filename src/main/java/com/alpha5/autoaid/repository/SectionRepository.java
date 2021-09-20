package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Section;
import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findBySectionName(String sectionName);
    Section findBySectionId(long sectionId);
    Section findByStaff(Staff staff);
}
