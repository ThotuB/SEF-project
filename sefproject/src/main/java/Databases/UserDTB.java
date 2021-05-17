package Databases;

import Components.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDTB {
    private ArrayList<User> data = new ArrayList<>();
    public final String path;

    public UserDTB(String path){
        this.path = new File(path).getAbsolutePath();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            data = new Gson().fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // VALIDATION (in need of it)
    public static boolean validUsername(User user){
        return user.getUsername().length() >= 5;
    }

    public static boolean validEmail(User user){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(user.getEmail());

        return matcher.matches();
    }

    public static boolean validPassword(User user){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,20}$");
        Matcher matcher = pattern.matcher(user.getPassword());

        return matcher.matches();
    }

    // EXISTANCE (is pain)
    public boolean existsUser(User user){
        if ( data == null ){
            return false;
        }
        for (User u : data) {
            if ( u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail()) ){
                user.setSalt(u.getSalt());
                user.setPasswordHashed();

                if ( u.getPasswordHashed().equals(user.getPasswordHashed()) ) {
                    return true;
                }
                else {
                    break;
                }
            }
        }

        return false;
    }

    public boolean existsUsernameOrEmail(User user){
        if ( data != null ){
            for (User u : data) {
                if ( u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail()) ){
                    return true;
                }
            }
        }

        return false;
    }

    // DATABASE OPTIONS
    public void remove(String username){
        data.removeIf(user -> user.getUsername().equals(username));
    }

    public void add(User user){
        data.add(user);
    }

    public void updateDatabase(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = gson.toJson(data);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void print(){
        if ( data == null ){
            return;
        }
        for (User u : data) {
            System.out.println(u);
        }
    }
}
