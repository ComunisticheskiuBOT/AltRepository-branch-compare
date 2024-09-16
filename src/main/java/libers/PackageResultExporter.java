package libers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PackageResultExporter{

    public static void exportComparisonResult(ComparisonResult result, String branch1, String branch2) throws IOException {
        String inBranch1NotInBranch2File = "inBranch1NotInBranch2.txt";
        String inBranch2NotInBranch1File = "inBranch2NotInBranch1.txt";
        String higherInBranch1File = "higherInBranch1.txt";

        writeListToFile(inBranch1NotInBranch2File, result.getInBranch1NotInBranch2(),
                "Пакеты в " + branch1 + " но не в " + branch2);

        writeListToFile(inBranch2NotInBranch1File, result.getInBranch1NotInBranch1(),
                "Пакеты в " + branch2 + " но не в " + branch1);

        writeListToFile(higherInBranch1File, result.getHigherInBranch1(),
                "Пакеты с новейшей версией в " + branch1);

        System.out.println("Результаты выгружены в файлы:");
        System.out.println(inBranch1NotInBranch2File);
        System.out.println(inBranch2NotInBranch1File);
        System.out.println(higherInBranch1File);
    }

    private static void writeListToFile(String fileName, List<PackageInfo> packages, String header) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(header);
            writer.newLine();
            for (PackageInfo pkg : packages) {
                writer.write(pkg.toString());
                writer.newLine();
            }
        }
    }
}
