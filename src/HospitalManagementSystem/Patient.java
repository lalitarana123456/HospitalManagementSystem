package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//access modifiers + encapsulation
public class Patient {
    private Connection connection;

    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.println("Enter Patient Name : ");
        String name = scanner.next();

        System.out.println("Enter Patient Age : ");
        int age = scanner.nextInt();

        System.out.println("Enter Patient Gender : ");
        String gender = scanner.next();


        try{

            //1- create prepared statement
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //set the value --> VALUES(?,?,?)
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);

            //2- insert data to the database + execution
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Patients data inserted successfully....");
            }else{
                System.out.println("Failed to Patients details");
            }




        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void viewPatients(){
        String query = "Select * from patients";
        try{

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //resultSet ---> table date hold --> possible to more than 1 row
            ResultSet resultSet = preparedStatement.executeQuery();
            //how to print?
            System.out.println("All patients are...");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-12s | %-20s | %-18s | %-10s\n",id,name,age,gender);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
       String query = "SELECT * FROM patients WHERE id = ?";

       try{

           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1, id);

           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
               return true;
           }else{
               return false;
           }
               //System.out.println("id : " + patientId + "name : "+ name + "age : "+ age + "gender :" + gender);

       }catch (SQLException e){
           e.printStackTrace();
       }
        return false;
    }

}
