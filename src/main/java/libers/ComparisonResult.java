package libers;

import java.util.List;

public class ComparisonResult {
    private List<PackageInfo> inBranch1NotInBranch2;
    private List<PackageInfo> inBranch1NotInBranch1;
    private List<PackageInfo> higherInBranch1;

    public ComparisonResult(List<PackageInfo> inBranch1NotInBranch2, List<PackageInfo> inBranch1NotInBranch1, List<PackageInfo> higherInBranch1){
        this.inBranch1NotInBranch1 = inBranch1NotInBranch1;
        this.inBranch1NotInBranch2 = inBranch1NotInBranch2;
        this.higherInBranch1 = higherInBranch1;
    }

    public List<PackageInfo> getInBranch1NotInBranch2() {
        return inBranch1NotInBranch2;
    }

    public void setInBranch1NotInBranch2(List<PackageInfo> inBranch1NotInBranch2) {
        this.inBranch1NotInBranch2 = inBranch1NotInBranch2;
    }

    public List<PackageInfo> getInBranch2NotInBranch1() {
        return inBranch1NotInBranch1;
    }

    public void setInBranch1NotInBranch1(List<PackageInfo> inBranch1NotInBranch1) {
        this.inBranch1NotInBranch1 = inBranch1NotInBranch1;
    }

    public List<PackageInfo> getHigherInBranch1() {
        return higherInBranch1;
    }

    public void setHigherInBranch1(List<PackageInfo> higherInBranch1) {
        this.higherInBranch1 = higherInBranch1;
    }
}
