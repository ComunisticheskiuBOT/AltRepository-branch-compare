package libers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PackageComparator {
    public static ComparisonResult comparePackages(List<PackageInfo> branch1, List<PackageInfo> branch2) {
        if (branch1 == null || branch2 == null) {
            throw new IllegalArgumentException("Один из списков пакетов равен null.");
        }

        Map<String, PackageInfo> branch1Map = branch1.parallelStream()
                .filter(pkg -> pkg != null && pkg.getName() != null)
                .collect(Collectors.toMap(
                        PackageInfo::getName,
                        Function.identity(),
                        (pkg1, pkg2) -> {
                            return pkg1;
                        }
                ));

        Map<String, PackageInfo> branch2Map = branch2.parallelStream()
                .filter(pkg -> pkg != null && pkg.getName() != null)
                .collect(Collectors.toMap(
                        PackageInfo::getName,
                        Function.identity(),
                        (pkg1, pkg2) -> pkg1
                ));

        List<PackageInfo> inBranch1NotBranch2 = branch1Map.keySet().stream()
                .filter(name -> !branch2Map.containsKey(name))
                .map(branch1Map::get)
                .collect(Collectors.toList());

        List<PackageInfo> inBranch2NotBranch1 = branch2Map.keySet().stream()
                .filter(name -> !branch1Map.containsKey(name))
                .map(branch2Map::get)
                .collect(Collectors.toList());

        List<PackageInfo> higherInBranch1 = branch1Map.entrySet().stream()
                .filter(entry -> branch2Map.containsKey(entry.getKey()) &&
                        entry.getValue().getVersion().compareTo(branch2Map.get(entry.getKey()).getVersion()) > 0)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());


        return new ComparisonResult(inBranch1NotBranch2, inBranch2NotBranch1, higherInBranch1);
    }
}

