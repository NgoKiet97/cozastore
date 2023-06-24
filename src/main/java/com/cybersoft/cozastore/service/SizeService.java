package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.imp.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<SizeResponse> getAllSize() {
        List<SizeEntity> list = sizeRepository.findAll();
        List<SizeResponse> listResponse = new ArrayList<>();

        for (SizeEntity data: list) {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(data.getId());
            sizeResponse.setName(data.getName());

            listResponse.add(sizeResponse);
        }

        return listResponse;
    }
}
