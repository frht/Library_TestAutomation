package com.library.test_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksManagementTestData {

    private static final List<String> showRecordsDropdownExpectedList = new ArrayList<>(
            Arrays.asList("5","10","15","50","100","200","500")
    );

    public static List<String> getShowRecordsDropdownExpectedList() {
        return showRecordsDropdownExpectedList;
    }
}
