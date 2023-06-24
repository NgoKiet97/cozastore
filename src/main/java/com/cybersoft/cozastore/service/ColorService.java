package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.service.imp.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<ColorResponse> getAllColor() {
        List<ColorEntity> colorEntityList = colorRepository.findAll();
        List<ColorResponse> colorResponseList = new ArrayList<ColorResponse>();

        for(ColorEntity entity : colorEntityList){
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(entity.getId());
            colorResponse.setName(entity.getName());

            colorResponseList.add(colorResponse);
        }
        return colorResponseList;
    }
}
