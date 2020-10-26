package com.cg.censusanalysis.statecodedata;

import com.cg.censusanalysis.CSVIllegalDelimiterException;
import com.cg.censusanalysis.CSVIllegalHeaderException;
import com.cg.censusanalysis.IllegalFilePathException;
import com.cg.censusanalysis.IllegalFileTypeException;
import com.cg.censusanalysis.statecensusdata.StateCensusPOJO;
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

public class StateCodeAnalyser {
    private ArrayList<StateCodePOJO> stateCodePOJOArrayList = new ArrayList<StateCodePOJO>();
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
            if (!(headerArray[0].equals("SrNo") &&
                    headerArray[1].equals("State Name") &&
                    headerArray[2].equals("TIN") &&
                    headerArray[3].equals("State Code")
            )) throw new CSVIllegalHeaderException("Wrong Type header in CSV file");
        }
    }

    public void readStateCodeFromCSV() throws IOException, IllegalFilePathException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        Reader reader;
        try {
        checkCSVDelimiter();
        checkCSVHeader();
            reader = Files.newBufferedReader(path);
        } catch (NoSuchFileException e) {
            throw new IllegalFilePathException("wrong file path");
        }

        CsvToBean<StateCodePOJO> csvToBean = new CsvToBeanBuilder(reader)
                .withType(StateCodePOJO.class)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<StateCodePOJO> stateCodePOJOIterator = csvToBean.iterator();
        while (stateCodePOJOIterator.hasNext()) {

            StateCodePOJO stateCodePOJO = stateCodePOJOIterator.next();
            stateCodePOJOArrayList.add(stateCodePOJO);

        }
    }

    public int getCount() {
        return stateCodePOJOArrayList.size();
    }
}
