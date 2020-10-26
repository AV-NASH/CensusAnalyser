package com.cg.censusanalysis;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class StateCensusPOJO {
    @CsvBindByPosition(position = 0)
    private String State;

    @CsvBindByPosition(position = 1)
    private String Population;

    @CsvBindByPosition(position = 2)
    private String AreaInSqKm;

    @CsvBindByPosition(position = 3)
    private String DensityPerSqKm;

    @Override
    public String toString() {
        return "State='" + State + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                ", DensityPerSqKm='" + DensityPerSqKm + '\'';
    }
}
