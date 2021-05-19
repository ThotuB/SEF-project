package Databases;

import Components.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CustomerDTB {
    private ArrayList<Customer> data = new ArrayList<>();
    private Customer currentCustomer;
    private final String path;

    public CustomerDTB(){
        path = "";
    }

    public CustomerDTB(String path){
        this.path = new File(path).getAbsolutePath();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            data = new Gson().fromJson(reader, new TypeToken<ArrayList<Customer>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // GETTERS
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Customer getCustomer(String username){
        if ( data == null ){
            return null;
        }
        for (Customer customer: data){
            if ( customer.getName().equals(username) ){
                return customer;
            }
        }

        return null;
    }

    public ArrayList<Customer> getData(){
        return data;
    }

    // SETTER
    public void setCurrentCustomer(String username) {
        currentCustomer = getCustomer(username);

        if ( currentCustomer == null ){
            currentCustomer = new Customer(username);

            add(currentCustomer);
            updateDatabase();
        }
    }


    public void add(Customer customer) {
        this.data.add(customer);
    }

    public void updateDatabase() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = gson.toJson(this.data);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CustomerDTB{" +
                "data=" + data +
                ", currentCustomer=" + currentCustomer +
                '}';
    }
}
