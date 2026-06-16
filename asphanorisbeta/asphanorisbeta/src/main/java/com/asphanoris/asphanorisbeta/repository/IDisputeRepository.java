package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.RealDispute;
import java.util.List;

public interface IDisputeRepository {
    RealDispute addDispute(RealDispute dispute);
    RealDispute modifyDispute(RealDispute dispute);
    void deleteDispute(RealDispute dispute);
    RealDispute getDispute(Long disputeId);
    List<RealDispute> getAllDisputes();
}