
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class VoterDao {

    public static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;

    static {
        try {
            ps = DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
            ps1 = DBConnection.getConnection().prepareStatement("select cname,party,symbol from candidate where candidate_id=?");
            ps2 = DBConnection.getConnection().prepareStatement("select candidate_id, cname,party,symbol from candidate , user_details where candidate.user_id = user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
            ps3 = DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
            ps5 = DBConnection.getConnection().prepareStatement("select candidate_id ,count(*) from voting group by candidate_id order by count(*) desc");
            ps6 = DBConnection.getConnection().prepareStatement("select candidate.party, count(voter_id) from candidate, voting  where candidate.candidate_id=voting.candidate_id  group by candidate.party");
            ps7 = DBConnection.getConnection().prepareStatement("select user_details.gen, count(*) from user_details, voting where user_details.adhar_no=voting.voter_id group by gen");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static CandidateInfo getVote(String voterId) throws Exception {
        ps.setString(1, voterId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return VoterDao.getCandidateInfo(rs.getString(1));
        }
        return null;
    }

    public static ArrayList<CandidateInfo> viewCandidate(String uid) throws Exception {
        ArrayList<CandidateInfo> candidateList = new ArrayList<>();
        ps2.setString(1, uid);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            CandidateInfo ci = new CandidateInfo();
            ci.setCandidateId(rs.getString(1));
            ci.setCname(rs.getString(2));
            ci.setParty(rs.getString(3));

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

            ci.setSymbol(base64Image);
            candidateList.add(ci);
        }
        return candidateList;
    }

    public static boolean addVote(VoteDTO vote) throws Exception {
        ps3.setString(1, vote.getCandidateId());
        ps3.setString(2, vote.getVoterId());
        return ps3.executeUpdate() == 1;
    }

    public static CandidateInfo getCandidateInfo(String cid) throws Exception {
        ps1.setString(1, cid);
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            CandidateInfo ci = new CandidateInfo();
            ci.setCandidateId(cid);
            ci.setCname(rs.getString(1));
            ci.setParty(rs.getString(2));

            String base64Image;
            Blob blob = rs.getBlob(3);
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

            ci.setSymbol(base64Image);
            return ci;
        }
        return null;
    }

    public static LinkedHashMap<String, Integer> getResult() throws Exception {
        LinkedHashMap<String, Integer> resultList = new LinkedHashMap<>();
        ResultSet rs = ps5.executeQuery();
        while (rs.next()) {
            resultList.put(rs.getString(1), rs.getInt(2));
        }
        return resultList;
    }

    public static LinkedHashMap<String, Integer> getResultByParty() throws Exception {
        LinkedHashMap<String, Integer> resultList = new LinkedHashMap<>();
        ResultSet rs = ps6.executeQuery();
        while (rs.next()) {
            resultList.put(rs.getString(1), rs.getInt(2));
        }
        return resultList;
    }

    public static HashMap<String, Integer> getMaleFemaleVote() throws Exception {
        HashMap<String, Integer> mf = new HashMap<>();
        ResultSet rs = ps7.executeQuery();
        while(rs.next()) {
            mf.put(rs.getString(1), rs.getInt(2));
        }
        return mf;
    }
}
