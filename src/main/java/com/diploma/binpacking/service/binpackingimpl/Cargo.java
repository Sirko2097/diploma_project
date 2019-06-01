package com.diploma.binpacking.service.binpackingimpl;


import java.util.ArrayList;

public class Cargo {

    ArrayList<Integer> getCargoList(String cargoOrder) {
        ArrayList<Integer> cargoList = new ArrayList<>();
        String[] s = cargoOrder.trim().split(" ");
        for (String cargo : s) {
            cargoList.add(Integer.parseInt(cargo));
        }

        return cargoList;
    }

}
