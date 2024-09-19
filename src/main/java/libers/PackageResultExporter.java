package libers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PackageResultExporter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void exportComparisonResult(ComparisonResult result, String branch1, String branch2) throws IOException {
        ResultOutput output = new ResultOutput();
        output.setFirstBranch(branch1);
        output.setSecondBranch(branch2);

        PackageList packageList = new PackageList();
        packageList.setArch("x86_64");

        packageList.setPackagesPresentedOnlyInFirstBranch(result.getInBranch1NotInBranch2());
        packageList.setPackagesPresentedOnlyInSecondBranch(result.getInBranch2NotInBranch1());
        packageList.setPackagesWithBiggerVersionInFirstBranch(result.getHigherInBranch1());

        output.setPackageList(List.of(packageList));

        // Записываем результат в JSON файл
        try (FileWriter fileWriter = new FileWriter("comparison_result.json")) {
            fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output));
        }

        System.out.println("Результаты выгружены в файл: comparison_result.json");
    }
}

class ResultOutput {
    private List<PackageList> packageList;
    private String firstBranch;
    private String secondBranch;

    public List<PackageList> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<PackageList> packageList) {
        this.packageList = packageList;
    }

    public String getFirstBranch() {
        return firstBranch;
    }

    public void setFirstBranch(String firstBranch) {
        this.firstBranch = firstBranch;
    }

    public String getSecondBranch() {
        return secondBranch;
    }

    public void setSecondBranch(String secondBranch) {
        this.secondBranch = secondBranch;
    }
}

class PackageList {
    private String arch;
    private List<PackageInfo> packagesPresentedOnlyInFirstBranch;
    private List<PackageInfo> packagesPresentedOnlyInSecondBranch;
    private List<PackageInfo> packagesWithBiggerVersionInFirstBranch;

    // Геттеры и сеттеры
    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public List<PackageInfo> getPackagesPresentedOnlyInFirstBranch() {
        return packagesPresentedOnlyInFirstBranch;
    }

    public void setPackagesPresentedOnlyInFirstBranch(List<PackageInfo> packagesPresentedOnlyInFirstBranch) {
        this.packagesPresentedOnlyInFirstBranch = packagesPresentedOnlyInFirstBranch;
    }

    public List<PackageInfo> getPackagesPresentedOnlyInSecondBranch() {
        return packagesPresentedOnlyInSecondBranch;
    }

    public void setPackagesPresentedOnlyInSecondBranch(List<PackageInfo> packagesPresentedOnlyInSecondBranch) {
        this.packagesPresentedOnlyInSecondBranch = packagesPresentedOnlyInSecondBranch;
    }

    public List<PackageInfo> getPackagesWithBiggerVersionInFirstBranch() {
        return packagesWithBiggerVersionInFirstBranch;
    }

    public void setPackagesWithBiggerVersionInFirstBranch(List<PackageInfo> packagesWithBiggerVersionInFirstBranch) {
        this.packagesWithBiggerVersionInFirstBranch = packagesWithBiggerVersionInFirstBranch;
    }
}
