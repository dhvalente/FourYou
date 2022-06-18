package br.com.foursys.fourcamp.fourbank.dto;

public class TransferDTO {
    private Double transferValue;
    private Integer payerId; // acountId de quem está recebendo = depósito
    private Integer receiverId; // acountId de quem está transferindo = saque

    public Double getTransferValue() {
        return transferValue;
    }
    public void setTransferValue(Double transferValue) {
        this.transferValue = transferValue;
    }

    public Integer getPayerId() {
        return payerId;
    }
    public void setPayerId(Integer payerId) {
        this.payerId = payerId;
    }
    public Integer getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
