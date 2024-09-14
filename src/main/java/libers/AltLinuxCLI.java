package libers;

import java.util.List;

import static libers.PackageResultExporter.exportComparisonResult;

public class AltLinuxCLI {
    public static void main(String[] args) throws  Exception {

        if(args.length != 2){
            System.out.println("Используйте: java AltLinuxCLI <branch1> <branch2>");
            return;
        }

        String branch1 = args[0];
        String branch2 = args[1];

        AltLinuxApi api = new AltLinuxApi();

        List<PackageInfo> branch1Packages = api.getPackages(branch1);
        List<PackageInfo> branch2Packages = api.getPackages(branch2);

        ComparisonResult result = PackageComparator.comparePackages(branch1Packages,branch2Packages);
        exportComparisonResult(result,branch1,branch2);
    }
}
