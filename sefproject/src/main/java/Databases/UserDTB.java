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
    private ArrayList<User> userDB = new ArrayList<User>();
    public final String path;

    public UserDTB(String path){
        this.path = new File(path).getAbsolutePath();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            userDB = new Gson().fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // VALIDATION
    public static boolean validUsername(User user){
        if ( user.getUsername().length() >= 5 ){
            return true;
        }
        return false;
    }

    public static boolean validEmail(User user){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(user.getEmail());

        if ( matcher.matches() ){
            return true;
        }
        return false;
    }

    public static boolean validPassword(User user){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,20}$");
        Matcher matcher = pattern.matcher(user.getPassword());

        if ( matcher.matches() ){
            return true;
        }
        return false;
    }

    // EXISTANCE
    public boolean existsUser(User user){
        if ( userDB != null ){
            for (User u : userDB) {
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
        }

        return false;
    }

    public boolean existsUsernameOrEmail(User user){
        if ( userDB != null ){
            for (User u : userDB) {
                if ( u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail()) ){
                    return true;
                }
            }
        }

        return false;
    }

    public void add(User user){
        userDB.add(user);
    }

    public void update(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = gson.toJson(userDB);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void print(){
        if ( userDB == null ){
            return;
        }
        for (User u : userDB) {
            System.out.println(u);
        }
    }
}
