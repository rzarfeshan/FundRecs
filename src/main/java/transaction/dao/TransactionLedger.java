package transaction.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import transaction.TransactionApplication;
import transaction.Model.TransactionBean;

@Component
public class TransactionLedger {

	final String transactionAlreadyExistsEvent = "Transaction with Date and Type already exists";

	public void save(List<TransactionBean> listTransactionBean) throws IOException {
		TreeSet<TransactionBean> currentCache = TransactionApplication.getCacheTransaction();

		for (TransactionBean bean : listTransactionBean) {
			if (!currentCache.contains(bean)) {
				currentCache.add(bean);
			} else {
				TransactionBean beanTmp = currentCache.ceiling(bean);
				writeEventLog(beanTmp,  bean);
				currentCache.remove(bean);
				bean.setAmount(beanTmp.getAmount() + bean.getAmount());
				currentCache.add(bean);

			}
		}

		String jsonStr = new Gson().toJson(currentCache);
		try (FileWriter file = new FileWriter("./src/main/resources/TransactionHistory.txt");) {
			file.write(jsonStr);
			System.out.println("Successfully Copied JSON Object to File...");

		} catch (IOException e) {
			throw new IOException(e.getLocalizedMessage());
		}
	}

	public List<TransactionBean> getTransaction(List<TransactionBean> beans) {
		TreeSet<TransactionBean> currentCache = TransactionApplication.getCacheTransaction();
		for (TransactionBean bean : beans) {
			if (currentCache.contains(bean)) {
				bean.setAmount(currentCache.ceiling(bean).getAmount());
			} else {
				bean.setAmount(0.0);
			}
		}

		return beans;
	}
	
	private void writeEventLog(TransactionBean oldBean, TransactionBean newBean) throws IOException {
		try (BufferedWriter br = new BufferedWriter(new FileWriter("./src/main/resources/EventHistory.txt", true))) {
			br.append(transactionAlreadyExistsEvent + ", OLD transaction: " + oldBean.toString() + 
					", New Transaction: " + newBean.toString());
			br.newLine();
			System.out.println("Event Written Successfully");

		} catch (IOException e) {
			throw new IOException(e.getLocalizedMessage());
		}
	}
}
