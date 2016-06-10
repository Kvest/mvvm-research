package com.kvest.mvvm_research.utils;

import com.kvest.mvvm_research.common.data.DataSource;
import static org.mockito.Mockito.*;

/**
 * Created by kvest on 10.06.16.
 */
public class Injection {
    public static DataSource provideDataSource() {
        return mock(DataSource.class);
    }
}
