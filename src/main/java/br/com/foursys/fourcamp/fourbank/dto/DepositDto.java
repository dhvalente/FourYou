package br.com.foursys.fourcamp.fourbank.dto;

public class DepositDto {

    private Double depositValue;
    private Integer accountId;

    public Double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(Double depositValue) {
        this.depositValue = depositValue;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
