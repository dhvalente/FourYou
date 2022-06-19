package br.com.foursys.fourcamp.fourbank.dto;

public class WithdrawDTO {
    private Double withdrawValue;
    private Integer accountId;

    public Double getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(Double withdrawValue) {
        this.withdrawValue = withdrawValue;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
