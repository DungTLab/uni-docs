/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Q3 {
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
	
                    int T;
                    StringBuilder result = new StringBuilder();
                    ArrayList<String> lineList = new ArrayList<>();
                    ArrayList<VehicleType> vehicleTypes = new ArrayList<>();
                    ArrayList<Vehicle> vehicles = new ArrayList<>();

	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    String fi, fo;
    
    /**
     * Set input and output file for automatic rating
     * @param args path of input file and path of output file
     */
    public void setFile (String[] args){
        fi = args.length>=2? args[0]: inputFile;
        fo = args.length>=2? args[1]: outputFile;
    }
    
    /**
     * Reads data from input file
     */
    public void read(){
        try (Scanner sc  = new Scanner(new File(fi))){
    //--END FIXED PART----------------------------

            //INPUT - @STUDENT: ADD YOUR CODE FOR INPUT HERE:

                    T = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < T; i++){
                        lineList.add(sc.nextLine());
                    }

	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
            sc.close();
        }catch(FileNotFoundException ex){
            System.out.println("Input Exception # " + ex);
        }
    }
    //--END FIXED PART----------------------------
    
    //ALGORITHM - @STUDENT: ADD YOUROWN METHODS HERE (IF NEED):
    

    
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve(){
    //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
                    for (String s : lineList){
                        String arr[] = s.trim().split(",");
                        if (arr[0].equalsIgnoreCase("AddVehicleType")){
                            VehicleType newType = new VehicleType(arr[1], arr[2]);
                            vehicleTypes.add(newType);
                            result.append(newType.entry()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("PrintVehicleType")){
                             result.append("---PrintVehicleType---\n");
                            if (vehicleTypes.isEmpty()){
                                result.append("Empty\n");
                            } else {
                                for (VehicleType v : vehicleTypes){
                                    result.append(v.print()).append("\n");
                                }
                            }
                            result.append("---\n");
                        } else if (arr[0].equalsIgnoreCase("AddVehicle")){
                            VehicleType v = new VehicleType();
                            for (VehicleType vehicle : vehicleTypes){
                                if (vehicle.getTypeId().equals(arr[3])){
                                    v = vehicle;
                                }
                            }
                            Vehicle newVehicle = new Vehicle(arr[1], Integer.parseInt(arr[2]), v);
                             vehicles.add(newVehicle);
                             result.append(newVehicle.entry()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("PrintVehicle")){
                            result.append("---PrintVehicle---\n");
                            if (vehicles.isEmpty()){
                                result.append("Empty\n");
                            } else {
                                for (Vehicle v : vehicles){
                                    result.append(v.print()).append("\n");
                                }
                            }
                            result.append("---\n");
                        } else if (arr[0].equalsIgnoreCase("SearchVehicle")){
                            result.append("---Search Result---\n");
                            int found = 0;
                            for (Vehicle v : vehicles){
                                if (v.getLicensePlate().equals(arr[1])){
                                    result.append(v.print()).append("\n");
                                    found = 1;
                                    break;
                                }    
                            }
                            if (found == 0){
                                    result.append("Not found\n");
                            }
                            result.append("---\n");
                        } else if (arr[0].equalsIgnoreCase("ClearVehicle")){
                            vehicles.clear();
                            result.append("* Remove all vehicles\n");
                        }
                    }
                    System.out.println(result.toString());

	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
    }
    
    /**
     * Write result into output file
     */
    public void printResult(){
	    try{
            FileWriter fw = new FileWriter(fo);
	//--END FIXED PART----------------------------
                
        	//OUTPUT - @STUDENT: ADD YOUR CODE FOR OUTPUT HERE:
            fw.write(result.toString());



	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
            fw.flush();
            fw.close();
        }catch (IOException ex){
            System.out.println("Output Exception # " + ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Q3 q = new Q3();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}