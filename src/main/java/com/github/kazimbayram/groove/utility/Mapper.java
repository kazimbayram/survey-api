package com.github.kazimbayram.groove.utility;

import java.util.List;

public interface Mapper {

    <T, K> T map(K source, Class<T> type);

    <T, K> List<T> map(List<K> source, Class<T> clazz);
}
