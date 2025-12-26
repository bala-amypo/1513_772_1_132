@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
        ((com.example.demo.service.impl.UserProfileServiceImpl) userProfileService).deleteUser(id);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "User deleted successfully"
        ));
    } catch (ResourceNotFoundException e) {
        throw e; // This will return 404 automatically
    } catch (OperationException e) {
        throw new RuntimeException(e.getMessage()); // Keep test compatibility
    }
}

@PutMapping("/{id}/deactivate")
public ResponseEntity<?> deactivate(@PathVariable Long id) {
    try {
        userProfileService.deactivateUser(id);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "User deactivated successfully"
        ));
    } catch (ResourceNotFoundException e) {
        throw e; // This will return 404 automatically
    } catch (OperationException e) {
        throw new RuntimeException(e.getMessage()); // Keep test compatibility
    }
}