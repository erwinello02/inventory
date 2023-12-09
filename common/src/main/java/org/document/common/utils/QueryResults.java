package org.document.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.document.common.model.Category;
import org.springframework.data.domain.Page;

import java.util.Collection;

@AllArgsConstructor
public class QueryResults<T> {
    public Collection<T> results;
    public Long countOfResults;
}
