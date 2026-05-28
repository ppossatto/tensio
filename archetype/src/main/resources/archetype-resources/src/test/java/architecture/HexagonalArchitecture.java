package ${package}.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class HexagonalArchitectureTest {

  private final JavaClasses classes = new ClassFileImporter()
     .importPackages("${package}");

  @Test
  void domainShouldNotDependOnAdapters() {
    ArchRule rule = noClasses()
       .that().resideInAPackage("..domain..")
       .should().dependOnClassesThat()
       .resideInAPackage("..adapter..");
    rule.check(classes);
  }

  @Test
  void domainShouldNotDependOnSpring() {
    ArchRule rule = noClasses()
       .that().resideInAPackage("..domain..")
       .should().dependOnClassesThat()
       .resideInAnyPackage(
          "org.springframework..",
          "jakarta.persistence.."
       );
    rule.check(classes);
  }

  @Test
  void adaptersShouldNotDependOnEachOther() {
    ArchRule rule = noClasses()
       .that().resideInAPackage("..adapter.in..")
       .should().dependOnClassesThat()
       .resideInAPackage("..adapter.out..");
    rule.check(classes);
  }

  @Test
  void applicationShouldNotDependOnAdapters() {
    ArchRule rule = noClasses()
       .that().resideInAPackage("..application..")
       .should().dependOnClassesThat()
       .resideInAPackage("..adapter..");
    rule.check(classes);
  }
}