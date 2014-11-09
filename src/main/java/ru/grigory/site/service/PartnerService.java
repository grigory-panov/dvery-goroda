package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.PartnerDao;
import ru.grigory.site.domain.Partner;
import ru.grigory.site.exception.BusinessException;

import java.util.List;

/**
 * Created by grigory on 02.11.14.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PartnerService {

    @Autowired
    private PartnerDao partnerDao;

    public List<Partner> findAll(){
        return partnerDao.findAll();
    }

    public Partner findById(long partnerId) {
        return partnerDao.findById(partnerId);
    }

    @Transactional(readOnly = false)
    public void addPartner(Partner partner){
        if(partner == null){
            throw new NullPointerException("partner is null");
        }
        if(partner.getId() != null){
            throw new BusinessException("non-empty id is not allowed");
        }
        partnerDao.add(partner);
    }

    @Transactional(readOnly = false)
    public void updatePartner(Partner partner){
        if(partner == null){
            throw new NullPointerException("partner is null");
        }
        if(partner.getId() == null){
            throw new BusinessException("empty id is not allowed");
        }
        partnerDao.update(partner);
    }

    @Transactional(readOnly = false)
    public void deletePartner(Long partnerId){
        partnerDao.delete(partnerId);
    }
}
