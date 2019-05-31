package com.diploma.binpacking.service.impl;

import com.diploma.binpacking.dao.CarDAO;
import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.service.CargoService;
import com.diploma.binpacking.service.binpackingimpl.LorryPacking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoServiceImpl  implements CargoService {

    private CarDAO carDAO;

    @Autowired
    public CargoServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public String packCargo(PackRequest packRequest) {
        LorryPacking packing = new LorryPacking(carDAO.getAll());
        packing.setCargoOrder(packRequest.getCargo());
        return packing.getAnswer();
    }
}
