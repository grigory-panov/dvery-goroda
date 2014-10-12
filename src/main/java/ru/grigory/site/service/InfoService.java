package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.InfoDao;
import ru.grigory.site.domain.Info;
import ru.grigory.site.exception.BusinessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    public List<Info> findAll(){
        return infoDao.findAll();
    }

    public Info findById(Long id){
        return infoDao.findById(id);
    }

    public Info findByIdWithDeleted(Long id){
        return infoDao.findByIdWithDeleted(id);
    }

    @Transactional(readOnly = false)
    public void addInfo(Info info){
        if(info.getId() != null){
            throw new BusinessException("non-empty id is not allowed");
        }
        infoDao.add(info);
    }

    @Transactional(readOnly = false)
    public void updateInfo(Info info){
        if(info.getId() == null){
            throw new BusinessException("empty id is not allowed");
        }
        infoDao.update(info);
    }

    @Transactional(readOnly = false)
    public void deleteInfo(Info info){
        infoDao.delete(info.getId());
    }

    @Transactional(readOnly = false)
    public void restoreInfo(Info info) {
        infoDao.restore(info.getId());
    }

    public List<Info> findDeleted() {
        return infoDao.findDeleted();
    }
}
