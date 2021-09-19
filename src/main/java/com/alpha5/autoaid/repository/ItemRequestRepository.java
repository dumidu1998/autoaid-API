package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.ItemRequestStatus;
import com.alpha5.autoaid.model.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest,Long> {

    @Query(value = "SELECT * FROM item_request INNER JOIN repairs ON item_request.repair_id=repair.repair_id WHERE item_request.repair_id=?1 AND status='COMPLTED'", nativeQuery = true)
    List<ItemRequest> findAllByRepair_repairIdAndStatus(long repairId);
    List<ItemRequest> findAllByRepair_repairIdAndStatusIs(long repairId, ItemRequestStatus itemRequestStatus);
//    List<ItemRequest> findAllByStatus(enum ItemRequestStatus.COMPLETED);
}
