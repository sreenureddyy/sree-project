package com.sree.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class PagedListTest extends TestCase {

	private Object testObject;
	private int expectedPage;

	public void setUp() {
	}

	public void testPaging1() {
		final int DATASIZE = 9;

		PagedList<Integer, Object> pagedList = new PagedList<Integer, Object>(
				new TestablePagedDataProvider1(), testObject);
		assertEquals(pagedList.size(), DATASIZE);

		expectedPage = 0;
		Integer n1 = pagedList.get(0);
		Integer n2 = pagedList.get(1);
		Integer n3 = pagedList.get(2);
		assertEquals((Integer) 1, (Integer) n1);
		assertEquals((Integer) 2, (Integer) n2);
		assertEquals((Integer) 3, (Integer) n3);

		expectedPage = 1;
		Integer n4 = pagedList.get(3);
		Integer n5 = pagedList.get(4);
		Integer n6 = pagedList.get(5);
		assertEquals((Integer) 4, (Integer) n4);
		assertEquals((Integer) 5, (Integer) n5);
		assertEquals((Integer) 6, (Integer) n6);

		expectedPage = 2;
		Integer n7 = pagedList.get(6);
		Integer n8 = pagedList.get(7);
		Integer n9 = pagedList.get(8);
		assertEquals((Integer) 7, (Integer) n7);
		assertEquals((Integer) 8, (Integer) n8);
		assertEquals((Integer) 9, (Integer) n9);
	}

	public void testPaging2() {
		final int DATASIZE = 9;

		PagedList<Integer, Object> pagedList = new PagedList<Integer, Object>(
				new TestablePagedDataProvider1(), testObject);
		assertEquals(pagedList.size(), DATASIZE);

		expectedPage = 1;
		Integer n4 = pagedList.get(3);
		Integer n5 = pagedList.get(4);
		Integer n6 = pagedList.get(5);
		assertEquals((Integer) 4, (Integer) n4);
		assertEquals((Integer) 5, (Integer) n5);
		assertEquals((Integer) 6, (Integer) n6);

		expectedPage = 2;
		Integer n7 = pagedList.get(6);
		Integer n8 = pagedList.get(7);
		Integer n9 = pagedList.get(8);
		assertEquals((Integer) 7, (Integer) n7);
		assertEquals((Integer) 8, (Integer) n8);
		assertEquals((Integer) 9, (Integer) n9);

		expectedPage = 0;
		Integer n1 = pagedList.get(0);
		Integer n2 = pagedList.get(1);
		Integer n3 = pagedList.get(2);
		assertEquals((Integer) 1, (Integer) n1);
		assertEquals((Integer) 2, (Integer) n2);
		assertEquals((Integer) 3, (Integer) n3);
	}

	public void testPaging3() {
		final int DATASIZE = 5;

		PagedList<Integer, Object> pagedList = new PagedList<Integer, Object>(
				new TestablePagedDataProvider2(), null);
		assertEquals(pagedList.size(), DATASIZE);

		expectedPage = 0;
		Integer n1 = pagedList.get(0);
		Integer n2 = pagedList.get(1);
		assertEquals((Integer) 1, (Integer) n1);
		assertEquals((Integer) 2, (Integer) n2);

		expectedPage = 1;
		Integer n3 = pagedList.get(2);
		Integer n4 = pagedList.get(3);
		assertEquals((Integer) 3, (Integer) n3);
		assertEquals((Integer) 4, (Integer) n4);

		expectedPage = 2;
		Integer n5 = pagedList.get(4);
		assertEquals((Integer) 5, (Integer) n5);
	}

	private class TestablePagedDataProvider1 implements
			PagedDataProvider<Integer, Object> {
		public List<Integer> provide(int page, Object queryParameters) {
			assertSame(queryParameters, testObject);
			List<Integer> results = new ArrayList<Integer>();
			switch (page) {
			case 0:
				if (expectedPage != 0) {
					fail("requested wrong page: 0");
				}
				results.add(1);
				results.add(2);
				results.add(3);
				break;
			case 1:
				if (expectedPage != 1) {
					fail("requested wrong page: 1");
				}
				results.add(4);
				results.add(5);
				results.add(6);
				break;
			case 2:
				if (expectedPage != 2) {
					fail("requested wrong page: 2");
				}
				results.add(7);
				results.add(8);
				results.add(9);
				break;
			default:
				fail("requested wrong page");
			}
			return results;
		}

		public int getDataSize() {
			return 9;
		}

		public int getPageSize() {
			return 3;
		}
	}

	private class TestablePagedDataProvider2 implements
			PagedDataProvider<Integer, Object> {
		public List<Integer> provide(int page, Object queryParameters) {
			assertNull(queryParameters);
			List<Integer> results = new ArrayList<Integer>();
			switch (page) {
			case 0:
				if (expectedPage != 0) {
					fail("requested wrong page: 0");
				}
				results.add(1);
				results.add(2);
				break;
			case 1:
				if (expectedPage != 1) {
					fail("requested wrong page: 1");
				}
				results.add(3);
				results.add(4);
				break;
			case 2:
				if (expectedPage != 2) {
					fail("requested wrong page: 2");
				}
				results.add(5);
				break;
			default:
				fail("ongeldige pagina opgevraagd");
			}
			return results;
		}

		public int getDataSize() {
			return 5;
		}

		public int getPageSize() {
			return 2;
		}
	}
}