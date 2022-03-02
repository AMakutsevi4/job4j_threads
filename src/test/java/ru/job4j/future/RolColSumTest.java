package ru.job4j.future;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class RolColSumTest {
    @Test
    public void whenSum() {
        RolColSum.Sums[] rsl = RolColSum.sum(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }
        );
        assertThat(rsl[0].getRowSum(), is(6));
        assertThat(rsl[1].getColSum(), is(15));
        assertThat(rsl[2].getColSum(), is(18));
        assertThat(rsl[2].getRowSum(), is(24));
    }

    @Test
    public void whenAsyncSum() {
        RolColSum.Sums[] rsl = RolColSum.asyncSum(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }
        );
        assertThat(rsl[0].getRowSum(), is(6));
        assertThat(rsl[0].getColSum(), is(12));
        assertThat(rsl[1].getColSum(), is(15));
        assertThat(rsl[1].getRowSum(), is(15));
        assertThat(rsl[2].getColSum(), is(18));
        assertThat(rsl[2].getRowSum(), is(24));
    }
}