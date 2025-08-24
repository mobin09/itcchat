package com.itc.insurancehelper.policy;

import  jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Policy controller class
// with CRUD operations
// and pagination support for list operation
// all endpoints should be secured
// except for the list operation
// use ResponseEntity to return responses
// use @Valid for request body validation
// return 404 Not Found for non-existing resources
// return 400 Bad Request for validation errors
// return 201 Created for successful creation
// return 200 OK for successful retrieval and updates
// return 204 No Content for successful deletion
// use constructor injection for the repository


@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {
    private final PolicyRepository repository;

    @PostMapping
    public ResponseEntity<Policy> create(@Valid @RequestBody Policy p) {
        return ResponseEntity.ok(repository.save(p));
    }
    @GetMapping
    public Page<Policy> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Policy> get(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Policy> update(@PathVariable Long id, @Valid @RequestBody Policy p) {
        return repository.findById(id).map(e -> { p.setId(e.getId()); return ResponseEntity.ok(repository.save(p)); }).orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}")
     public ResponseEntity<Policy> patch(@PathVariable Long id, @RequestBody Policy p) {
        return repository.findById(id).map(e -> {
               if (p.getPolicyNumber()!=null) e.setPolicyNumber(p.getPolicyNumber());
               if (p.getType()!=null) e.setType(p.getType());
               if (p.getPremium()!=null) e.setPremium(p.getPremium());
               if (p.getStartDate()!=null) e.setStartDate(p.getStartDate());
               if (p.getEndDate()!=null) e.setEndDate(p.getEndDate());
               if (p.getStatus()!=null) e.setStatus(p.getStatus());
               return ResponseEntity.ok(repository.save(e)); }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
