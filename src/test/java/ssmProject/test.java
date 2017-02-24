package ssmProject;

import java.util.List;

import com.model.SortModel;

public interface test {
	SortModel selectSort(int id);

    List<SortModel> allSort();

    <T> List<SortModel> querySort(T model);
}
