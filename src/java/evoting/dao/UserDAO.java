
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class UserDAO {

    public static PreparedStatement ps, ps2, ps3;

    static {
        try {
            ps = DBConnection.getConnection().prepareStatement("Select user_type from user_details where adhar_no=? and password=?");
            ps2 = DBConnection.getConnection().prepareStatement("Select gen,count(*) from user_details group by gen");
            ps3 = DBConnection.getConnection().prepareStatement(" select username, city, mobile_no, email, gen from user_details order by username");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String validateUser(UserDTO user) throws SQLException {
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static HashMap<String, Integer> getMaleAndFemale() throws Exception {
        HashMap<String, Integer> mf = new HashMap<>();
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + "" + rs.getInt(2));
            mf.put(rs.getString(1), rs.getInt(2));
        }
        System.out.println("From userDao " + mf);
        return mf;
    }

    public static ArrayList<UserData> getAllUsers() throws Exception {
        ArrayList<UserData> users = new ArrayList<>();
        ResultSet rs = ps3.executeQuery();
        UserData d = null;
        while (rs.next()) {
            d = new UserData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0));
            users.add(d);
        }
        return users;
    }
    

}