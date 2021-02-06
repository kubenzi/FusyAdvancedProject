package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;

import java.util.List;

public class OperationBuilder {
    private List<OperationDto> spendingOperationsDto;
    private List<OperationDto> balanceOperationsDto;

    public static final class Builder {

        private List<OperationDto> spendingOperationsDto;
        private List<OperationDto> balanceOperationsDto;

        public Builder spending(List<OperationDto> spendingOperationsDto) {
            this.spendingOperationsDto = spendingOperationsDto;
            this.balanceOperationsDto = spendingOperationsDto;
            return this;
        }

        public Builder balance(List<OperationDto> balanceOperationsDto) {
            this.balanceOperationsDto = balanceOperationsDto;
            return this;
        }

        public OperationBuilder OperationBuilder() {

            OperationBuilder operationBuilder = new OperationBuilder();
            if (spendingOperationsDto.isEmpty()) {
                throw new IllegalStateException("Name cannot be empty");
            }
            operationBuilder.spendingOperationsDto = this.spendingOperationsDto;
            operationBuilder.balanceOperationsDto = this.balanceOperationsDto;

            return operationBuilder;
        }
    }
}
