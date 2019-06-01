package com.diploma.binpacking.service.impl;

import com.diploma.binpacking.dao.CarDAO;
import com.diploma.binpacking.model.Car;
import com.diploma.binpacking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    @Autowired
    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> getAll() {
        return carDAO.getAll();
    }
}
