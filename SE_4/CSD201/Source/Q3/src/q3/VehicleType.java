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
public class VehicleType implements IManageable{
    private String typeId;
    private String typeName;

    public VehicleType() {
    }

    public VehicleType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    @Override
    public String entry(){
        return String.format("(%s,%s) is added", typeId, typeName);
    }
    
    @Override
    public String print(){
        return String.format("(%s,%s)", typeId, typeName);
    }
}
