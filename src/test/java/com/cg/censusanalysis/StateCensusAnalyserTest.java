package com.cg.censusanalysis;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class StateCensusAnalyserTest {
    String CORRECT_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\StateCensusData.csv";
    String WRONG_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\Census\\StateCensusData.csv";
    String WRONG_TYPE_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\sample.docx";
    String WRONG_DELIMITER_CSV_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\newDelimiterCSV.csv";
    String WRONG_HEADER_CSV_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\StateCensusDataWrongHeader.csv";
    @Test
    public void givenCsvFileReturnsCorrectNumberOfRecords() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {

        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        stateCensusAnalyser.loadCSVFile(CORRECT_FILE_PATH);
        stateCensusAnalyser.readStateCensusFromCSV();
        Assert.assertEquals(29,stateCensusAnalyser.getCount());
    }

    @Test(expected = IllegalFilePathException.class)
    public void givenCsvFileThrowsExceptionWhenWrongPath() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        stateCensusAnalyser.loadCSVFile(WRONG_FILE_PATH);
        stateCensusAnalyser.readStateCensusFromCSV();

    }

    @Test(expected = IllegalFileTypeException.class)
    public void whenGivenWrongFIleTypeThrowsException() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        stateCensusAnalyser.loadCSVFile(WRONG_TYPE_FILE_PATH);

            stateCensusAnalyser.readStateCensusFromCSV();
    }

    @Test(expected = CSVIllegalDelimiterException.class)
    public void whenGivenWrongTypeDelimiterThrowException() throws IllegalFileTypeException, IOException, IllegalFilePathException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        stateCensusAnalyser.loadCSVFile(WRONG_DELIMITER_CSV_PATH);

        stateCensusAnalyser.readStateCensusFromCSV();
    }

    @Test(expected = CSVIllegalHeaderException.class)
    public void whenGivenWrongHeaderFormatThrowException() throws IllegalFileTypeException, CSVIllegalDelimiterException, IllegalFilePathException, CSVIllegalHeaderException, IOException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        stateCensusAnalyser.loadCSVFile(WRONG_HEADER_CSV_PATH);
        stateCensusAnalyser.readStateCensusFromCSV();
    }

}