package application.adapter;

import com.google.common.base.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageConverter <T,V> implements Page<V> {

    static public class Builder<T> {
        private Page<T> source;

        private Builder(Page<T> source) {
            this.source = source;
        }

        public <V> Page<V> using(Function<T, V> converter){
            return new PageConverter<>(source,converter);
        }
    }

    public static <T> Builder<T> convert(Page<T> page){
        return new Builder<>(page);
    }

    private final Page<T> base;
    private final List<V> result;

    private PageConverter(Page<T> base,Function<T,V> converter) {
        this.base = base;
        this.result = new ArrayList<>();
        for(T object : base) {
            result.add(converter.apply(object));
        }
    }

    public List<V> getContent() {
        return result;
    }

    public int getNumber() {
        return base.getNumber();
    }

    public int getNumberOfElements() {
        return base.getNumberOfElements();
    }

    public int getSize() {
        return base.getSize();
    }

    public Sort getSort() {
        return base.getSort();
    }

    public long getTotalElements() {
        return base.getTotalElements();
    }

    @Override
    public <U> Page<U> map(java.util.function.Function<? super V, ? extends U> converter) {
        return null;
    }

    public int getTotalPages() {
        return base.getTotalPages();
    }

    public boolean hasContent() {
        return base.hasContent();
    }

    public boolean hasNext() {
        return base.hasNext();
    }

    public boolean hasPrevious() {
        return base.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    public boolean isFirst() {
        return base.isFirst();
    }

    public boolean isLast() {
        return base.isLast();
    }

    public Iterator<V> iterator() {
        return result.iterator();
    }
}