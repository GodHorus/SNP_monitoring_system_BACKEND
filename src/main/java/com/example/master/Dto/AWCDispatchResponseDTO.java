package com.example.master.Dto;

public class AWCDispatchResponseDTO {

    private Long id;
    private String remark;
    private Integer dispatchPackets;

    // From PackagingDetail
    private Long packagingId;
    private Double availableStock;
    private Double packetSize;
    private String unit;
    private Integer packets;
    private Double remainingStock;

    // From BatchDetail
    private String batchNo;
    private String lotNo;
    private String cdpo;
    private String qrCode;

    // Getters & Setters
    // ...

}
