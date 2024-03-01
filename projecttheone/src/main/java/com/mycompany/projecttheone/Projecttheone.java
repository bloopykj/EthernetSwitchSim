package com.mycompany.projecttheone;
import java.io.*;
import java.util.*;
/**
*
* @author bloopykj
*/
public class Projecttheone {
    static String frameID;
    public static String broadcast = "X";
    static String arrivalPort;
    static String sourceAddress;
    static String destinationAddress;
    static Map<String, ArrayList<String>> port_addresses = new HashMap<>(); // Holds the port numbers and the Source Addresses
    static Map<String, ArrayList<String>> port_frames = new HashMap<>(); // Holds the port numbers and the frameIDs
    static {
        port_addresses.put("P1", new ArrayList<String>());
        port_addresses.put("P2", new ArrayList<String>());
        port_addresses.put("P3", new ArrayList<String>());
        port_addresses.put("P4", new ArrayList<String>());
        port_addresses.put("P5", new ArrayList<String>());
        port_addresses.put("P6", new ArrayList<String>());
        port_addresses.put("P7", new ArrayList<String>());
        port_addresses.put("P8", new ArrayList<String>());
        port_frames.put("P1", new ArrayList<String>());
        port_frames.put("P2", new ArrayList<String>());
        port_frames.put("P3", new ArrayList<String>());
        port_frames.put("P4", new ArrayList<String>());
        port_frames.put("P5", new ArrayList<String>());
        port_frames.put("P6", new ArrayList<String>());
        port_frames.put("P7", new ArrayList<String>());
        port_frames.put("P8", new ArrayList<String>());
    }
    
    public static void readFile(String input){
        File file = new File(input);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while((line = br.readLine()) != null){
                String[] tokens = line.split("\\s+");
                frameID = tokens[0];
                arrivalPort = tokens[1];
                String[] split = tokens[2].split("--");
                sourceAddress = split[0];
                destinationAddress = split[1];
                processFrame(frameID, arrivalPort, sourceAddress, destinationAddress);
            }
        } catch(FileNotFoundException e){
            System.out.println("The file is not found");
        } catch(IOException p){
            System.out.println("IOException cat");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pastes output to out.txt
    public static void pasteFile(String output){
        BufferedWriter bf = null;
        try {
            File pasteOutput = new File(output);
            bf = new BufferedWriter(new FileWriter(pasteOutput));
            // iterates though port_frames and pastes key and its values
            for(String key : port_frames.keySet()) {
                ArrayList<String> temp = port_frames.get(key);
                bf.write("\n" + key + ": " + temp);
                bf.newLine();
            }
            bf.flush();
        } catch(IOException e) {
            System.out.println("IOException Error");
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void processFrame(String frameID, String arrivalPort, String sourceAddress, String destinationAddress){
        boolean framenotfound = true;
        if(!destinationAddress.equals(broadcast)){
            // search for destination addresses through port_addresses hashmap
            /*
            Check if port and source address are not stored in the hashmap
            */
            if(!port_addresses.get(arrivalPort).contains(sourceAddress)){
                // Add sourceAddress to port arraylist
                port_addresses.get(arrivalPort).add(sourceAddress);
            }
            /*
            Search through all ports in port_addresses if it has an address matching the argument destinationAddress above
            If yes, add the frameID argument above to the port_frames arraylist using the SAME KEY
            Break out of loop
            */
            for(String key: port_addresses.keySet()){
                for(int i = 0; i < port_addresses.values().size(); i++){
                // if current port_address has destination address, add frame to port_frame frame list
                    if(port_addresses.get(key).contains(destinationAddress)){
                        port_frames.get(key).add(frameID);
                        framenotfound = false;
                        break;
                        }
                }
            }
            /*
            If none of the ports in the port_addresses has the destinationAddress value, flood the port_frames
            */
            if(framenotfound == true){
                // flood all ports except the argument arrival port
                // loop through all ports in the ports_frame list
                // if the key is not the same as the argument arrival port, add the frameID
                for(String key: port_frames.keySet()){
                // If key is not equal to arrival port
                // Add frameID to arraylist of port
                    if(!key.equals(arrivalPort)){
                        port_frames.get(key).add(frameID);
                    }
                }
            }
        } else if (destinationAddress.equals(broadcast)) {
        // Broadcast
            for(String key: port_frames.keySet()){
                if(!key.equals(arrivalPort)){
                    port_frames.get(key).add(frameID);
                }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        readFile("re.txt");
        pasteFile("rw.txt");
    }
}