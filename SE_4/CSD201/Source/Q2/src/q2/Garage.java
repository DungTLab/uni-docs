/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q2;

/**
 *
 * @author LENOVO
 */

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class Garage {
    Map<String, Vehicle> vehicles = new LinkedHashMap<>();
    
    public boolean addVehicle(Vehicle v){
        if (vehicles.containsKey(v.getLicensePlate())){
            return false;
        } else {
            vehicles.put(v.getLicensePlate(), v);
            return true;
        }
    }
    
    public Vehicle findVehicle(String licensePlate){
        return vehicles.get(licensePlate);
    }
    
    public boolean removeVehicle(String licensePlate){
        if (vehicles.containsKey(licensePlate)){
            vehicles.remove(licensePlate);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateVehicle(String licensePlate, String newMake, String newModel, Integer newYear, String newOwner){
        if (vehicles.containsKey(licensePlate)){
            Vehicle vehicleUpdate  = vehicles.get(licensePlate);
            vehicleUpdate.setMake(newMake);
            vehicleUpdate.setModel(newModel);
            vehicleUpdate.setYear(newYear);
            vehicleUpdate.setOwnerName(newOwner);
            return true;
        } else {
            return false;
        }
    }
    
    public List<Vehicle> listAllVehicles(){
        ArrayList<Vehicle> v = new ArrayList<>(vehicles.values());
        return v;   
    }
}
