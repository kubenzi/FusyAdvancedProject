package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.Operation;

import java.util.List;

public interface OperationService {

    List<Operation> getAllOperations();

    Operation getOperationById(Long id);

    Operation addOperation(Operation operation);
}
