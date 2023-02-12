
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateDto;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;


public class CandidateDao {

    private static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8;

    static {
        try {
            ps = DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
            ps1 = DBConnection.getConnection().prepareStatement("Select username from user_details where adhar_no=?");
            ps2 = DBConnection.getConnection().prepareStatement("Select distinct city from user_details");
            ps3 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select candidate_id from candidate");
            ps5 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
            ps6 = DBConnection.getConnection().prepareStatement("Update candidate set party=?, city=?, symbol=? where candidate_id=?");
            ps7 = DBConnection.getConnection().prepareStatement("Delete from Candidate where candidate_id=?");
            ps8 = DBConnection.getConnection().prepareStatement("select * from candidate where user_id=? and city=?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getNewCandidateId() throws SQLException {
        ResultSet rs = ps.executeQuery();
        rs.next();
        String cid = rs.getString(1);
        if (cid == null) {
            return "C101";
        } else {

            int id = Integer.parseInt(cid.substring(1));
            return "C" + (id + 1);
        }

    }

    public static String getUsernameById(String uid) throws SQLException {
        ps1.setString(1, uid);
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    public static ArrayList<String> getCity() throws SQLException {
        ArrayList<String> cityList = new ArrayList<>();
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            cityList.add(rs.getString(1));
        }
        return cityList;

    }

    public static boolean addCandidate(CandidateDto candidate) throws SQLException {
        ps3.setString(1, candidate.getCandidateId());
        ps3.setString(2, candidate.getParty());
        ps3.setString(3, candidate.getUserId());
        ps3.setBinaryStream(4, candidate.getSymbol());
        ps3.setString(5, candidate.getCity());
        ps3.setString(6, candidate.getCname());

        return (ps3.executeUpdate() != 0);
    }

    public static ArrayList<String> getAllCandidateIDs() throws SQLException {
        ArrayList<String> candidateIDs = new ArrayList<>();
        ResultSet rs = ps4.executeQuery();
        while (rs.next()) {
            candidateIDs.add(rs.getString(1));
        }
        return candidateIDs;
    }

    public static CandidateDetails getDetailsById(String cid) throws Exception {
        CandidateDetails candidate = new CandidateDetails();
        ps5.setString(1, cid);
        ResultSet rs = ps5.executeQuery();
        if (rs.next()) {
            candidate.setCandidateId(rs.getString(1));
            candidate.setParty(rs.getString(2));
            candidate.setUserId(rs.getString(3));
            //convert Blob to String
            String base64Image;
            Blob blob = rs.getBlob(4);
            byte imageBytes[] = null;
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            candidate.setSymbol(base64Image);

            candidate.setCity(rs.getString(5));
            candidate.setCname(rs.getString(6));
            return candidate;
        }
        return null;
    }

    public static boolean updateCandidate(CandidateDto cand) throws Exception {
        ps6.setString(1, cand.getParty());
        ps6.setString(2, cand.getCity());
        ps6.setBinaryStream(3, cand.getSymbol());
        ps6.setString(4, cand.getCandidateId());
        return (ps6.executeUpdate() != 0);
    }

    public static boolean deleteCandidate(String cid) throws Exception {
        ps7.setString(1, cid);
        return ps7.executeUpdate() != 0;
    }

    public static boolean checkCandidate(String userid, String city) throws Exception {
        ps8.setString(1, userid);
        ps8.setString(2, city);
        ResultSet rs=ps8.executeQuery();
        return rs.next();
    }
}
