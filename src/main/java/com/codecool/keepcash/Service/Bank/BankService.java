package com.codecool.keepcash.Service.Bank;

import com.codecool.keepcash.Dto.Bank.BankDto;
import com.codecool.keepcash.Entity.Bank;
import com.codecool.keepcash.Repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {
    private BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDto> getAllBanks() {
        return convertListToDto((List<Bank>) bankRepository.findAll());
    }

    private BankDto convertBankToBankDto(Bank bank) {
        return new BankDto(bank.getId(), bank.getName());
    }

    private List<BankDto> convertListToDto(List<Bank> banks) {
        return banks.stream().map(bank -> convertBankToBankDto(bank)).collect(Collectors.toList());
    }
}
