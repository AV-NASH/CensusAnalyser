package com.cg.censusanalysis.statecodedata;

import com.opencsv.bean.CsvBindByPosition;

public class StateCodePOJO {
    @CsvBindByPosition(position = 0)
    private String SrNo;

    @CsvBindByPosition(position = 1)
    private String State_name;

    @CsvBindByPosition(position = 2)
    private String TIN;

    @CsvBindByPosition(position = 3)
    private String State_code;
}
