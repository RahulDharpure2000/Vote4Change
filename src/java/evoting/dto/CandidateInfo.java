
package evoting.dto;

import java.util.Objects;


public class CandidateInfo {

    private String candidateId;
    private String party;
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

    @Override
    public String toString() {
        return "CandidateInfo{" + "candidateId=" + candidateId + ", party=" + party + ", symbol=" + symbol + ", cname=" + cname + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.candidateId);
        hash = 71 * hash + Objects.hashCode(this.party);
        hash = 71 * hash + Objects.hashCode(this.symbol);
        hash = 71 * hash + Objects.hashCode(this.cname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateInfo other = (CandidateInfo) obj;
        if (!Objects.equals(this.candidateId, other.candidateId)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.cname, other.cname)) {
            return false;
        }
        return true;
    }

    
}
