
package evoting.dto;


public class CandidateDetails {
    private String candidateId;
    private String party;
    private String city;
    private String userId;
    private String symbol;
    private String cname;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public CandidateDetails(String candidateId, String party, String city, String userId, String symbol, String cname) {
        this.candidateId = candidateId;
        this.party = party;
        this.city = city;
        this.userId = userId;
        this.symbol = symbol;
        this.cname = cname;
    }

    public CandidateDetails() {
    }

    @Override
    public String toString() {
        return "CandidateDetails{" + "candidateId=" + candidateId + ", party=" + party + ", city=" + city + ", userId=" + userId + ", symbol=" + symbol + ", cname=" + cname + '}';
    }

}
