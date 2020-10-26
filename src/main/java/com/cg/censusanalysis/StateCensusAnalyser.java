package com.cg.censusanalysis;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;


public class StateCensusAnalyser {
    private ArrayList<StateCensusPOJO> stateCensusPOJOArrayList = new ArrayList<StateCensusPOJO>();
    Path path;

    public void loadCSVFile(String FilePath) throws IllegalFileTypeException {
        if (FilenameUtils.getExtension(FilePath).equals("csv"))
            path = Paths.get(FilePath);
        else throw new IllegalFileTypeException("Wrong File Type,only csv files are allowed");
    }

    public void checkCSVDelimiter() throws IOException, CSVIllegalDelimiterException {
        BufferedReader bufferedReader = Files.newBufferedReader(path);
        String line;
        boolean notcontains_valid_delimiter = false;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.contains(",")) notcontains_valid_delimiter = true;
        }
        if (notcontains_valid_delimiter)
            throw new CSVIllegalDelimiterException("Illegal delimiter present in csv file");
    }

    public void checkCSVHeader() throws IOException, CSVIllegalHeaderException {
        BufferedReader bufferedReader = Files.newBufferedReader(path);
        String header;
        header = bufferedReader.readLine();
        String[] headerArray = header.split(",");
        if (headerArray.length != 4) throw new CSVIllegalHeaderException("Wrong Type header in CSV file");
        else {
            if (!(headerArray[0].equals("State") &&
                    headerArray[1].equals("Population") &&
                    headerArray[2].equals("AreaInSqKm") &&
                    headerArray[3].equals("DensityPerSqKm")
            )) throw new CSVIllegalHeaderException("Wrong Type header in CSV file");
        }
    }

    public void readStateCensusFromCSV() throws IOException, IllegalFilePathException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        checkCSVDelimiter();
        checkCSVHeader();
        Reader reader;
        try {
            reader = Files.newBufferedReader(path);
        } catch (NoSuchFileException e) {
            throw new IllegalFilePathException("wrong file path");
        }

        CsvToBean<StateCensusPOJO> csvToBean = new CsvToBeanBuilder(reader)
                .withType(StateCensusPOJO.class)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<StateCensusPOJO> stateCensusPOJOIterator = csvToBean.iterator();
        while (stateCensusPOJOIterator.hasNext()) {

            StateCensusPOJO stateCensusPOJO = stateCensusPOJOIterator.next();
            stateCensusPOJOArrayList.add(stateCensusPOJO);

        }
    }

    public int getCount() {
        return stateCensusPOJOArrayList.size();
    }
}
