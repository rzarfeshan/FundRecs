package transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;

import transaction.Model.TransactionBean;

@SpringBootApplication
@ComponentScan(basePackages = { "transaction" })
public class TransactionApplication {

	public static TreeSet<TransactionBean> cacheTransaction = null;
	private static final String TRANSACTION_HISTORY_FILENAME = "./src/main/resources/TransactionHistory.txt";

	public static TreeSet<TransactionBean> getCacheTransaction() {
		return cacheTransaction;
	}

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
		cacheTransaction = new TreeSet<>();
		readFileToCache();

	}

	private static void readFileToCache() {
		
		File file = new File(TRANSACTION_HISTORY_FILENAME);
		if (!file.exists()) {
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_HISTORY_FILENAME))) {
			String line = null;
			line = br.lines().collect(Collectors.joining());

			final ObjectMapper objectMapper = new ObjectMapper();
			TransactionBean[] transactions = null;

			transactions = objectMapper.readValue(line, TransactionBean[].class);

			cacheTransaction = new TreeSet<>(Arrays.asList(transactions));
			System.out.println(cacheTransaction);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
