package edu.thu.mltool4j.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import edu.thu.mltool4j.utils.ErrorReport;

public class Dataset
{
	private ArrayList<Data> datas;

	// total number of data
	private int dataNum = -1;

	// total number of distinct features
	private int featureNum = -1;

	public Dataset()
	{
		this.datas = new ArrayList<Data>();
		refreshStatistics();
	}

	public Dataset(ArrayList<Data> initDatas)
	{
		this.datas = initDatas;
		refreshStatistics();
	}

	public Dataset(File datafile)
	{
		this.datas = new ArrayList<Data>();
		try
		{
			List<String> datalines = FileUtils.readLines(datafile);
			for (int i = 0; i < datalines.size(); i++)
			{
				datas.add(new Data(i, datalines.get(i)));
			}
			refreshStatistics();
		}
		catch (IOException e)
		{
			ErrorReport.showMessage(this, "Dataset(File datafile)", e
					.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Do statistics
	 */
	private void refreshStatistics()
	{
		this.dataNum = this.datas.size();

		HashSet<Integer> calculator = new HashSet<Integer>();
		for (Data d : datas)
		{
			for (Feature f : d.getAllFeature())
			{
				calculator.add(f.dim);
			}
		}
		this.featureNum = calculator.size();
	}

	/**
	 * Get data at specific index
	 * 
	 * @param index
	 * @return
	 */
	public Data getDataAt(int index)
	{
		return datas.get(index);
	}

	/**
	 * Get all data object
	 * 
	 * @return
	 */
	public ArrayList<Data> getAllData()
	{
		return datas;
	}

	/**
	 * Get the total number of data in this dataset
	 * 
	 * @return
	 */
	public int size()
	{
		return this.dataNum;
	}

	/**
	 * Get the total number of distinct features in this dataset
	 * 
	 * @return the total number of distinct features in this dataset
	 */
	public int getFeatureNum()
	{
		return this.featureNum;
	}
}
