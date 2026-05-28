package ${package}.domain.exception;

public class ResourceNotFoundException extends DomainException {

  public ResourceNotFoundException(String resourceName, Object identifier) {
    super("Resource '%s' not found with identifier '%s'".formatted(resourceName, identifier));
  }
}