package com.cg.censusanalysis.statecodedata;

import com.cg.censusanalysis.CSVIllegalDelimiterException;
import com.cg.censusanalysis.CSVIllegalHeaderException;
import com.cg.censusanalysis.IllegalFilePathException;
import com.cg.censusanalysis.IllegalFileTypeException;
import com.cg.censusanalysis.statecensusdata.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StateCodeAnalyserTest {
    String CORRECT_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\StateCode.csv";
    String WRONG_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\Census\\StateCode.csv";
    String WRONG_TYPE_FILE_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\sample.docx";
    String WRONG_DELIMITER_CSV_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\newDelimiterCSV.csv";
    String WRONG_HEADER_CSV_PATH="C:\\Users\\Avinash\\IdeaProjects\\CensusAnalyser\\StateCodeWrongHeader.csv";
    @Test
    public void givenCsvFileReturnsCorrectNumberOfRecords() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {

        StateCodeAnalyser stateCodeAnalyser=new StateCodeAnalyser();
        stateCodeAnalyser.loadCSVFile(CORRECT_FILE_PATH);
        stateCodeAnalyser.readStateCodeFromCSV();
        Assert.assertEquals(37,stateCodeAnalyser.getCount());
    }

    @Test(expected = IllegalFilePathException.class)
    public void givenCsvFileThrowsExceptionWhenWrongPath() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCodeAnalyser stateCodeAnalyser=new StateCodeAnalyser();
        stateCodeAnalyser.loadCSVFile(WRONG_FILE_PATH);
        stateCodeAnalyser.readStateCodeFromCSV();

    }

    @Test(expected = IllegalFileTypeException.class)
    public void whenGivenWrongFIleTypeThrowsException() throws IOException, IllegalFilePathException, IllegalFileTypeException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCodeAnalyser stateCodeAnalyser=new StateCodeAnalyser();
        stateCodeAnalyser.loadCSVFile(WRONG_TYPE_FILE_PATH);
        stateCodeAnalyser.readStateCodeFromCSV();
    }

    @Test(expected = CSVIllegalDelimiterException.class)
    public void whenGivenWrongTypeDelimiterThrowException() throws IllegalFileTypeException, IOException, IllegalFilePathException, CSVIllegalDelimiterException, CSVIllegalHeaderException {
        StateCodeAnalyser stateCodeAnalyser=new StateCodeAnalyser();
        stateCodeAnalyser.loadCSVFile(WRONG_DELIMITER_CSV_PATH);
        stateCodeAnalyser.readStateCodeFromCSV();
    }

    @Test(expected = CSVIllegalHeaderException.class)
    public void whenGivenWrongHeaderFormatThrowException() throws IllegalFileTypeException, CSVIllegalDelimiterException, IllegalFilePathException, CSVIllegalHeaderException, IOException {
        StateCodeAnalyser stateCodeAnalyser=new StateCodeAnalyser();
        stateCodeAnalyser.loadCSVFile(WRONG_HEADER_CSV_PATH);
        stateCodeAnalyser.readStateCodeFromCSV();
    }
}