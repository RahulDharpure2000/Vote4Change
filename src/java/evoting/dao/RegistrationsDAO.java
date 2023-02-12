
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationsDAO {
    public static PreparedStatement ps,ps1;
    static
    {
        try {
            ps=DBConnection.getConnection().prepareStatement("Select * from User_details where adhar_no=?");
            ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static boolean searchUser(String userid) throws SQLException
    {
        ps.setString(1, userid);
        return ps.executeQuery().next();
    }
    public static boolean registerUser(UserDetails user)throws SQLException
    {
        ps1.setString(1, user.getUserid());
        ps1.setString(2, user.getPassword());
        ps1.setString(3, user.getUsername());
        ps1.setString(4, user.getAddress());
        ps1.setString(5, user.getCity());
        ps1.setLong(6, user.getMobile());
        ps1.setString(7,"Voter");
        ps1.setString(8, user.getEmail());
        ps1.setString(9, String.valueOf(user.getGender()));
        return ps1.executeUpdate()==1;
    }
}
