///**
// * 
// */
//package transaction.Controller;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.TreeSet;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.BlockJUnit4ClassRunner;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//import transaction.TransactionApplication;
//import transaction.Model.TransactionBean;
//import transaction.dao.TransactionLedger;
//
///**
// * @author Rabeya Zarfeshan
// *
// */
//@RunWith(value = BlockJUnit4ClassRunner.class)
//public class TransactionControllerTest {
//	
//
//	
//	List<TransactionBean> request1 = null;
//	
//	String badJson = "[\r\n"
//			+ "        \"date\": \"11-01-2023\",\r\n"
//			+ "        \"type\": \"credit\",\r\n"
//			+ "        \"amount\": 3001.08\r\n"
//			+ "    \r\n"
//			+ "]";
//
//	String getJsonRequest = "[\r\n"
//			+ "    {\r\n"
//			+ "        \"date\": \"11-12-2022\",\r\n"
//			+ "        \"type\": \"credit\"\r\n"
//			+ "    },\r\n"
//			+ "    {\r\n"
//			+ "        \"date\": \"11-01-2023\",\r\n"
//			+ "        \"type\": \"credit\"\r\n"
//			+ "    }\r\n"
//			+ "]";
//	
//	public TransactionControllerTest() {}
//		private TransactionController transactionController;
//		
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//   public void setUp() throws Exception {
//		request1 = new ArrayList<TransactionBean>();
//		TransactionBean bean = new TransactionBean();
//		bean.setDate("11-01-2019");
//		bean.setType("credit");
//		bean.setAmount(100.00);
//		request1.add(bean);
//		
//		TransactionBean bean1 = new TransactionBean();
//		bean1.setDate("11-12-2019");
//		bean1.setType("credit");
//		bean1.setAmount(100.00);
//		request1.add(bean1);
//		
//		TransactionApplication.cacheTransaction = initialize();
//		transactionController = new TransactionController();
//	}
//	
//	private TreeSet<TransactionBean> initialize() throws JsonProcessingException {
//		TreeSet<TransactionBean> init = new TreeSet<TransactionBean>();
//		TransactionBean bean = new TransactionBean();
//		bean.setDate("11-01-2019");
//		bean.setType("credit");
//		bean.setAmount(100.00);
//		init.add(bean);
//		
//		TransactionBean bean1 = new TransactionBean();
//		bean1.setDate("11-12-2019");
//		bean1.setType("credit");
//		bean1.setAmount(100.00);
//		init.add(bean1);
//		
//		TransactionBean bean2 = new TransactionBean();
//		bean2.setDate("11-12-2020");
//		bean2.setType("credit");
//		bean2.setAmount(100.00);
//		init.add(bean2);
//		
//		return init;
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//    public void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for {@link transaction.Controller.TransactionController#saveTransaction(java.lang.String)}.
//	 */
//	@Test
//	public void testSaveTransaction() {
//		transactionController.saveTransaction(request1);
//		TreeSet<TransactionBean> expected = TransactionApplication.getCacheTransaction();
//		assertEquals(expected.size(), 3);
//	}
//	
//	@Test
//	public final void testSaveTransaction1() {
//		transactionController.saveTransaction(request1);
//		TreeSet<TransactionBean> expected = TransactionApplication.getCacheTransaction();
//		assertEquals(expected.size(), 5);
//	}
//	
//	@Test 
//	public final void testSaveTransactionEXp() {
//		transactionController.saveTransaction(request1);
//	}
//
//	/**
//	 * Test method for {@link transaction.Controller.TransactionController#getTransactions(java.lang.String)}.
//	 * @throws JsonProcessingException 
//	 */
//	@Test
//	public final void testGetTransactions() throws JsonProcessingException {
//		transactionController.getTransactions(getJsonRequest);
//		
//		TransactionBean bean1 = new TransactionBean();
//		bean1.setDate("11-12-2019");
//		bean1.setType("credit");
//		bean1.setAmount(100.00);
//		
//		TreeSet<TransactionBean> expected = TransactionApplication.getCacheTransaction();
//		
//		Double actualAmount = expected.ceiling(bean1).getAmount();
//	//	assertEquals(100.00, actualAmount);
//		
//	}
//	
//	
//	@Test
//	public final void testGetBadTransactions() {
//		transactionController.getTransactions(badJson);
//	}
//
//}
