package com.dao;

import java.util.List;

import com.model.SortModel;

public interface IMyTestDao {
	SortModel selectSort(int id);

    List<SortModel> allSort();

    <T> List<SortModel> querySort(T model);
}
