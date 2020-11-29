package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> getAllOperations() {
        return (List<Operation>) operationRepository.findAll();
    }

    @Override
    public Operation getOperationById(Long id) {
        return operationRepository.findById(id).orElse(null);
    }

    @Override
    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }
}
