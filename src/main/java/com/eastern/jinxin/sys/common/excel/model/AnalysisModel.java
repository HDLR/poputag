package com.eastern.jinxin.sys.common.excel.model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisModel {
	protected int startRow=0;
	protected int endRow=1000000;
	protected int startSheet=0;
	protected int endSheet=0;
	// 列集合
	protected List<String> colums = new ArrayList<String>();

	public AnalysisModel(int startRow, List<String> colums) {
		this.startRow = startRow;
		this.colums = colums;
	}
	public AnalysisModel(int startRow,int endRow,int startSheet,int endSheet, List<String> colums) {
		this.startRow = startRow;
		this.endRow = endRow;
		this.startSheet = startSheet;
		this.endSheet = endSheet;
		this.colums = colums;
	}
	public AnalysisModel( List<String> colums) {
		this.colums = colums;
	}

	public int getStartRow() {
		return startRow;
	}

	public List<String> getColums() {
		return colums;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getStartSheet() {
		return startSheet;
	}

	public int getEndSheet() {
		return endSheet;
	}
	

}
