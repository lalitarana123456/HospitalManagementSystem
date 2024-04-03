package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection connection;

    public Doctor(Connection connection){
        this.connection = connection;

    }

    public void viewDoctors(){
        String query = "Select * from doctor ";
        try{

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //resultSet ---> table date hold --> possible to more than 1 row
            ResultSet resultSet = preparedStatement.executeQuery();
            //how to print?
            System.out.println("All doctors are...");
            while(resultSet.next()){
                int doctorId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specification = resultSet.getString("specification");

                System.out.printf("|%-12s|%-20s|%-18s|\n",doctorId,name,specification);
               // System.out.println("id : " + doctorId + "name : "+ name + "specification :" + specification);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctor WHERE id = ?";

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
