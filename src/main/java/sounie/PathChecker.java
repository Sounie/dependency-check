package sounie;

import io.github.classgraph.*;

import java.util.HashSet;
import java.util.Set;


public class PathChecker {
    private final String rootPackage;
    private final Set<String> unwantedPackages;

    public PathChecker(String rootPackage, Set<String> unwantedPackages) {
        this.rootPackage = rootPackage;
        this.unwantedPackages = unwantedPackages;
    }

    /**
     * Check whether there is a dependency relationship detectable from the rootPackage to any of the specified
     * unwanted packages.
     *
     * @return
     */
    public boolean checkDependencyPath() {
        boolean pathFound = false;

        Set<ClassInfo> classInfoSet = new HashSet<>();

        try (ScanResult scanResult = new ClassGraph()
                .verbose()
                .enableAllInfo()
                .enableInterClassDependencies()
                .scan(10))
        {
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                if (rootPackage.equals(classInfo.getPackageName())) {
                    // Start from classes under rootPackage
                    // iterate through methods / fields (imports?)
                    // Medium term - Build up structure without recursion
                    accumulateChildClasses(classInfoSet, classInfo);
                }
            }

            pathFound = isPathFoundWithMatchingName(classInfoSet);
            if (!pathFound) {
                pathFound = isPathFoundStartingWithUnwantedPackage(classInfoSet);
            }
        }

        return pathFound;
    }

    private boolean isPathFoundStartingWithUnwantedPackage(Set<ClassInfo> classInfoSet) {
        for (ClassInfo classInfo : classInfoSet) {
            // TODO: consider replacing with stream findAny
            for (String unwantedPackage : unwantedPackages) {
                if (classInfo.getPackageName().startsWith(unwantedPackage)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPathFoundWithMatchingName(Set<ClassInfo> classInfoSet) {
        boolean pathFound = false;

        for (ClassInfo classInfo : classInfoSet) {
            if (unwantedPackages.contains(classInfo.getPackageName())) {
                pathFound = true;

                break;
            }
        }

        return pathFound;
    }

    private static void accumulateChildClasses(Set<ClassInfo> classInfoSet, ClassInfo classInfo) {
        ClassInfoList classDependencies = classInfo.getClassDependencies();
        for (ClassInfo dependencyClassInfo : classDependencies) {
            if (!classInfoSet.contains(dependencyClassInfo)) {
                System.out.println("dependencies of " + dependencyClassInfo);
                classInfoSet.add(dependencyClassInfo);
                ClassInfoList nestedDependencies = classInfo.getClassDependencies();
                for (ClassInfo nestedDependency : nestedDependencies) {
                    System.out.println("checking level for " + nestedDependency.getName());
                    if (!classInfoSet.contains(nestedDependency)) {
                        accumulateChildClasses(classInfoSet, nestedDependency);
                        classInfoSet.add(nestedDependency);
                    }
                }
            } else {
                System.out.println("Already seen " + dependencyClassInfo);
            }
        }
    }
}
