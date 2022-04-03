package transaction.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import transaction.Model.TransactionBean;
import transaction.dao.TransactionLedger;

@Validated
@RestController
@RequestMapping("/api/v1")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class TransactionController {

	TransactionLedger transactionLedger;

	public TransactionController() {
		transactionLedger = new TransactionLedger();
	}

	@RequestMapping(value = "/transactions", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<String> saveTransaction(@Valid @RequestBody List<TransactionBean> transactionBean) {
		try {
			transactionLedger.save(transactionBean);

		} catch (Exception e) {
			System.out.println("Exception Occurs: " + e.getLocalizedMessage());
		}

		return new ResponseEntity<String>("Successfully Inserted", HttpStatus.OK);
	}

	@RequestMapping(value = "/transactions", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<String> getTransactions(@Valid @RequestBody List<TransactionBean> transactionBean) {
		String jsonStr = null;
		List<TransactionBean> response = null;

		try {
			response = transactionLedger.getTransaction(transactionBean);
			jsonStr = new Gson().toJson(response);
		} catch (Exception e) {
			System.out.println("Exception Occurs: " + e.getLocalizedMessage());
		}

		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}
}
