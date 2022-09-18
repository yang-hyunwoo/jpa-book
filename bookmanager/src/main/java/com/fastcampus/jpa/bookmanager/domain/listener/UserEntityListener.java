package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.HwUser;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import com.fastcampus.jpa.bookmanager.repository.UserHistoryRespository;
import com.fastcampus.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRespository userHistoryRespository = BeanUtils.getBean(UserHistoryRespository.class);
        HwUser user = (HwUser) o;
//        UserHistory userHistory = new UserHistory();
        var userHistory = new UserHistory();
//        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());

        userHistoryRespository.save(userHistory);
    }
}
