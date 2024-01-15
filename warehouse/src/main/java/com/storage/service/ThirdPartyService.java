package com.storage.service;

import com.storage.entity.ThirdParty;
import com.storage.repository.ThirdPartyRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ThirdPartyService {

    @Inject
    ThirdPartyRepository thirdPartyRepository;

    public List<ThirdParty> getAllThirdParty() {
        return thirdPartyRepository.findAll();
    }

    public List<ThirdParty> getAllThirdPartyForUser(boolean isPowerUser) {
        List<Long> lst = new ArrayList<>();
        lst.add(1L);
        if (isPowerUser) {
            lst.add(2L);
        }
        return thirdPartyRepository.findAllById(lst);
    }
}
