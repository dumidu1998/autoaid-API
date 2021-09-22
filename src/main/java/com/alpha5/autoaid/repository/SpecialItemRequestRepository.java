package com.alpha5.autoaid.repository;


import com.alpha5.autoaid.enums.SpecialItemRequestStatus;
import com.alpha5.autoaid.model.SpecialItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialItemRequestRepository extends JpaRepository<SpecialItemRequest,Long> {

    List<SpecialItemRequest> findAllByStatus(SpecialItemRequestStatus status);

    SpecialItemRequest findBySpecialRequestId(long reqid);
}
