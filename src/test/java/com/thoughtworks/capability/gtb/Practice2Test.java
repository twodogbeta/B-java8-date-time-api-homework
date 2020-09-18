package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author itutry
 * @create 2020-05-15_17:23
 */
public class Practice2Test {

  @Test
  public void test_getNextWorkDate() {
    LocalDate workDate1 = Practice2.getNextWorkDate(LocalDate.of(2020, 5, 14));
    Assert.assertEquals("2020-05-15", workDate1.toString());

    LocalDate workDate2 = Practice2.getNextWorkDate(LocalDate.of(2020, 5, 15));
    Assert.assertEquals("2020-05-18", workDate2.toString());


    LocalDate workDate3 = Practice2.getNextWorkDate(LocalDate.of(2020, 9, 19));
    Assert.assertEquals("2020-09-21", workDate3.toString());

    LocalDate workDate4 = Practice2.getNextWorkDate(LocalDate.of(2020, 9, 18));
    Assert.assertEquals("2020-09-21", workDate4.toString());
  }
}
