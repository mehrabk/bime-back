package com.project.bime.payload;

import lombok.Data;

@Data
public class ReportResponse {
    private int customers;
    private int bime;
    private int bimeSales;
    private int bimeBadane;
    private int both;
    private int havePolicy;
    private int notHavePolicy;
}
