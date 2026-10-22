/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3;

/**
 *
 * @author LENOVO
 */
public class Vehicle implements IManageable{
    private String licensePlate;
    private int entryHour;
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, int entryHour, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.entryHour = entryHour;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEntryHour() {
        return entryHour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEntryHour(int entryHour) {
        this.entryHour = entryHour;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String entry(){
        return String.format("(%s) is added", licensePlate);
    }
    
    public String print(){
        return String.format("(%s,%d):[%s]", licensePlate, entryHour, vehicleType.getTypeName());
    }
}
